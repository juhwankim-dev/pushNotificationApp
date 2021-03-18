package com.juhwan.anyang_yi.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.juhwan.anyang_yi.network.AriApplyNoticeNetwork
import com.juhwan.anyang_yi.network.Result
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class AriApplyNoticeRepository {
    private val parameter: MutableMap<String, String> = HashMap()

    var _ariApplyNotice = MutableLiveData<String>()

    fun getAriApplyNotices() {
        parameter["now"] = "0"

        val call = AriApplyNoticeNetwork.getJsonApi().boardListPost(parameter)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(
                call: Call<ResponseBody>,
                response: Response<ResponseBody>
            ) {
                if(response.isSuccessful) {
                    try {
                        _ariApplyNotice.value = response.body()!!.string()
                        Log.v("2222222222222",response.body()!!.string())
                    } catch (e: Exception) {
                        Log.v("7777777", e.message.toString())
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.v("88888888", t.message.toString())
            }
        })
    }
}