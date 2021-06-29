package com.juhwan.anyang_yi.repository

import androidx.lifecycle.MutableLiveData
import com.juhwan.anyang_yi.data.*
import com.juhwan.anyang_yi.network.ApplyApi
import com.juhwan.anyang_yi.ui.SplashActivity
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.HashMap

object InitialRepository {
    var sf = SimpleDateFormat("yyyy-MM-dd")
    private var today = LocalDate.now()
    private var date1 = "$today 00:00:00"
    var todayDate = sf.parse(date1)

    var apply = ArrayList<Apply>()
    var ariNotice = ArrayList<AriNoticeList>()
    var html = MutableLiveData<String>()

    fun loadInitialData() {
        loadApplyNotice()
    }

    private fun loadApplyNotice() {
        val parameterApply: MutableMap<String, String> = HashMap()
        parameterApply["now"] = "0"
        val call = ApplyApi.createApi().getNotice(parameterApply)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if(response.isSuccessful) {
                    try {
                        html.value = response.body()!!.string()
                    } catch (e: Exception) {

                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }
        })
    }

    fun parsingApplyNotice(){
        var doc = Jsoup.parse(html.value)

        var programList = doc.select(".list_program")[0]
        var elementDDay = programList.select(".txt_1")
        var elementImage = programList.select(".img img")
        var elementTitle = programList.select(".tit a")
        var elementUrl = programList.select(".tit a")
        var elementPeriod = programList.select(".line")
        var elementApplicant = programList.select(".app strong")

        for(i in 0 until elementTitle.size){
            apply.add(
                Apply(
                    elementImage[i].attr("src"),
                    elementTitle[i].text(),
                    elementDDay[i].text().replace("ay", "").replace("종료", "0"),
                    elementUrl[i].attr("href"),
                    elementPeriod[i * 2 + 1].text(),
                    elementApplicant[i * 2].text() + "/" + elementApplicant[i * 2 + 1].text()
                )
            )
        }

        apply.reverse()
    }
}