package com.juhwan.anyang_yi.repository

import androidx.lifecycle.MutableLiveData
import com.juhwan.anyang_yi.network.MainNoticeNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap
import com.juhwan.anyang_yi.network.Result

class MainNoticeRepository {
    private val parameterForMain: MutableMap<String, String> = HashMap()

    var _entireNotice = MutableLiveData<Result>()
    var _univNotice = MutableLiveData<Result>()

    init {
        parameterForMain["menuId"] = "23"
        parameterForMain["bsIdx"] = "61"
    }

    fun getMainNotices(page: Int, menu: String) {
        when (menu) {
            "전체" -> parameterForMain["bcIdx"] = "0"
            "대학교" -> parameterForMain["bcIdx"] = "20"
        }
        parameterForMain["page"] = page.toString()

        val call = MainNoticeNetwork.getJsonApi().boardListPost(parameterForMain)

        call.enqueue(object : Callback<Result> {
            override fun onResponse(
                call: Call<Result>,
                response: Response<Result>
            ) {
                if (response.isSuccessful) {
                    try {
                        when (menu) {
                            "전체" -> _entireNotice.value = response.body()
                            "대학교" -> _univNotice.value = response.body()
                        }
                    } catch (e: Exception) {

                    }
                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {

            }
        })
    }
}
/*    fun lookUpNotice(keyword: String){
        parameter["bcIdx"] = "0"
        parameter["searchCondition"] = "SUBJECT"
        parameter["searchKeyword"] = keyword
        Log.v("11111111111","11111111111")
        val call = NoticeNetwork.getJsonApi().boardListPost(parameter)

        CoroutineScope(Dispatchers.Main).launch {
            val temp = CoroutineScope(Dispatchers.IO).async {
                call.enqueue(object : Callback<Result> {
                    override fun onResponse(
                        call: Call<Result>,
                        response: Response<Result>
                    ) {
                        if (response.isSuccessful) {
                            try {
                                Log.v("22222222222222","222222222222222")
                            } catch (e: Exception) {

                            }
                        }
                    }

                    override fun onFailure(call: Call<Result>, t: Throwable) {

                    }
                })
            }.await()
            Log.v("33333","333333333333333333333")
        }

        Log.v("4444444444444","44444444444444444")
    }*/
