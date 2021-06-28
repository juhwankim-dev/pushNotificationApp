package com.juhwan.anyang_yi.repository

import androidx.lifecycle.MutableLiveData
import com.juhwan.anyang_yi.data.AriNotice
import com.juhwan.anyang_yi.data.AriNoticeList
import com.juhwan.anyang_yi.network.AriNoticeApi
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class AriNoticeRepository {
    private val parameterAriNotice: MutableMap<String, String> = HashMap()
    var _ariNotice = MutableLiveData<AriNotice>()

    init {
        parameterAriNotice["schWord"] = " "
        parameterAriNotice["noneChk"] = "2"
        parameterAriNotice["bbsidx"] ="21"
    }

    fun loadAriNotice(page: Int) {
        parameterAriNotice["pageNo"] = page.toString()

        val call = AriNoticeApi.createApi().getNotice(parameterAriNotice)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    try{
                        var notice = ArrayList<AriNoticeList>()

                        var doc = Jsoup.parse(response.body()!!.string())
                        var elementTitle = doc.select(".alignL a")
                        var elementDate = doc.select(".alignC")

                        for((i, e) in elementTitle.withIndex()){
                            notice.add(AriNoticeList(e.attr("href"), e.text(), elementDate[i * 4 + 2].text().replace("/", "-")))
                        }

                        notice.add(AriNoticeList(" ", " ", " ")) // 프로그레스바를 위치할 곳

                        _ariNotice.value = AriNotice(notice)
                    }catch (e: Exception){

                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }
        })
    }

    fun loadInitialAriNotice() {
        parameterAriNotice["pageNo"] = "1"

        val call = AriNoticeApi.createApi().getNotice(parameterAriNotice)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    try{
                        var notice = ArrayList<AriNoticeList>()

                        var doc = Jsoup.parse(response.body()!!.string())
                        var elementTitle = doc.select(".alignL a")
                        var elementDate = doc.select(".alignC")

                        for((i, e) in elementTitle.withIndex()){
                            notice.add(AriNoticeList(e.attr("href"), e.text(), elementDate[i * 4 + 2].text().replace("/", "-")))
                        }

                        InitialRepository.ariNotice.addAll(notice.subList(0, 5))
                    }catch (e: Exception){

                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }
        })
    }
}