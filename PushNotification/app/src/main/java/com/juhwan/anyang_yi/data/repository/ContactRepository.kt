package com.juhwan.anyang_yi.data.repository

import androidx.lifecycle.MutableLiveData
import com.juhwan.anyang_yi.data.model.Contact
import com.juhwan.anyang_yi.data.api.ContactNetwork
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ContactRepository {

    var contact = ArrayList<Contact>()
    var departmentList = ArrayList<String>()
    lateinit var arr: Array<Array<String>>

    var isFinished = MutableLiveData<Boolean>()

    fun requestPost() {
        val call = ContactNetwork.getJsonApi().boardListPost()
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    try {
                        arrangeHtml(response.body()!!.string())
                        getDepartmentList()
                    } catch (e: Exception) {

                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }
        })
    }

    private fun arrangeHtml(html: String) {
        var doc = Jsoup.parse(html)

        var tr = doc.select("tr")
        arr = Array(tr.size){Array(6) {""}}

        // 0, 1, 2, 3, 4, 5
        // 대, 중, 위치, 소, 번호, 팩스

        for (i in 1 until tr.size) { // 세로
            var td = tr[i].select("td")
            var k = 0
            for(j in 0..5){ // 가로
                if(arr[i][j] == ""){ // 이미 채워져있는 공간은 건너뛰기 위함
                    if(td[k].hasAttr("colspan") && td[k].hasAttr("rowspan")){
                        insertColRow(i, j, td[k].text(), td[k].attr("colspan").toInt(), td[k].attr("rowspan").toInt())
                    } else if(td[k].hasAttr("colspan")){
                        insertColspan(i, j, td[k].text(), td[k].attr("colspan").toInt())
                    } else if(td[k].hasAttr("rowspan")){
                        insertRowspan(i, j, td[k].text(), td[k].attr("rowspan").toInt())
                    } else {
                        if(td[k].text() == ""){
                            arr[i][j] = "."
                        } else {
                            arr[i][j] = td[k].text()
                        }
                    }
                    k++
                }
            }
        }

        for(i in 1 until tr.size){
            contact.add(
                Contact(
                    arr[i][0].replace(" ", ""),
                arr[i][1].replace(" ", "").replace(".", ""),
                arr[i][3].replace(".", ""),
                arr[i][4])
            )
        }
    }

    private fun insertColRow(i:Int, j:Int, text: String, repeatCol: Int, repeatRow: Int){
        for(m in j until j+repeatCol){
            if(text == ""){
                arr[i][m] = "."
            } else {
                arr[i][m] = text
            }

            for(l in i until i+repeatRow){
                if(text == ""){
                    arr[l][m] = "."
                } else {
                    arr[l][m] = text
                }
            }
        }
    }

    private fun insertColspan(i: Int, j: Int, text: String, repeat: Int){
        for(m in j until j+repeat){
            if(text == ""){
                arr[i][m] = "."
            } else {
                arr[i][m] = text
            }
        }
    }

    private fun insertRowspan(i: Int, j: Int, text: String, repeat: Int) {
        for(m in i until i+repeat){
            if(text == ""){
                arr[m][j] = "."
            } else {
                arr[m][j] = text
            }
        }
    }

    private fun getDepartmentList() {
        var contactSize = contact.size

        departmentList.add("") // 맨 첫 아이템은 '전체'를 넣을거임
        
        for (i in 1 until contactSize) {
            if(!departmentList.contains(contact[i].lClass)){
                departmentList.add(contact[i].lClass)
            }
        }

        isFinished.value = true
    }
}