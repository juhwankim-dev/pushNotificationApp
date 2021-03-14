package com.juhwan.anyang_yi.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.juhwan.anyang_yi.network.NoticeNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap
import com.juhwan.anyang_yi.network.Result
import com.juhwan.anyang_yi.network.ResultList

class NoticeRepository {



/*    private val parameter: MutableMap<String, String> = HashMap()
    var _noticeInfo = MutableLiveData<Result>()

    init {
        parameter["menuId"] = "23"
        parameter["bsIdx"] = "61"
        parameter["bcIdx"] = "0"
    }

    fun requestPost(page: Int) {
        parameter["page"] = page.toString()
        val call = NoticeNetwork.getJsonApi().boardListPost(parameter)

        call.enqueue(object : Callback<Result> {
            override fun onResponse(
                call: Call<Result>,
                response: Response<Result>
            ) {
                if(response.isSuccessful) {
                    try {
                        _noticeInfo.value = response.body()
                    } catch (e: Exception) {

                    }
                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {

            }
        })
    }*/
}