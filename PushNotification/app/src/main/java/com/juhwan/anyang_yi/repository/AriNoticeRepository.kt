package com.juhwan.anyang_yi.repository

import android.util.Log
import com.juhwan.anyang_yi.network.AriNotice
import com.juhwan.anyang_yi.network.AriNoticeNetwork
import com.juhwan.anyang_yi.ui.notice.menu.AriNoticeFragment
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class AriNoticeRepository {
    private val parameterForAri: MutableMap<String, String> = HashMap()

    init {
        parameterForAri["schWord"] = " "
        parameterForAri["noneChk"] = "2"
        parameterForAri["bbsidx"] ="21"
    }

    fun getAriNotices(
        page: Int,
        listener: AriNoticeFragment
    ){
        parameterForAri["pageNo"] = page.toString()

        val call = AriNoticeNetwork.getJsonApi().boardListPost(parameterForAri)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    var ariNotice = ArrayList<AriNotice>()

                    try{
                        var doc = Jsoup.parse(response.body()!!.string())

                        var elementTitle = doc.select(".alignL a")
                        var elementDate = doc.select(".alignC")

                        // 0, 1, 2 .. 10
                        for((i, e) in elementTitle.withIndex()){
                            ariNotice.add(AriNotice(e.attr("href"), e.text(), elementDate[i*4+2].text()))
                        }

                        listener.update(ariNotice)
                    }catch (e: Exception){

                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }
        })
    }
}