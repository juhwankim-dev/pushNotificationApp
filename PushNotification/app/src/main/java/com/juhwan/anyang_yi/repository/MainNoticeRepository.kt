package com.juhwan.anyang_yi.repository

import androidx.lifecycle.MutableLiveData
import com.juhwan.anyang_yi.network.MainNoticeNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap
import com.juhwan.anyang_yi.network.Result
import com.juhwan.anyang_yi.network.ResultList

object MainNoticeRepository {
    private val parameterForMain: MutableMap<String, String> = HashMap()

    var _mainNotice = MutableLiveData<Result>()
    var all = ArrayList<ResultList>()

    init {
        parameterForMain["menuId"] = "23"
        parameterForMain["bsIdx"] = "61"
        parameterForMain["bcIdx"] = "0"
    }

    fun getMainNotices(page: Int) {
        parameterForMain["page"] = page.toString()

        val call = MainNoticeNetwork.getJsonApi().boardListPost(parameterForMain)

        call.enqueue(object : Callback<Result> {
            override fun onResponse(
                call: Call<Result>,
                response: Response<Result>
            ) {
                if (response.isSuccessful) {
                    try {
                        all.addAll(response.body()!!.resultList)
                        _mainNotice.value = Result(all)
                    } catch (e: Exception) {

                    }
                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {

            }
        })
    }
}