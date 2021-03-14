package com.juhwan.anyang_yi.ui.notice

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.juhwan.anyang_yi.network.NoticeNetwork
import com.juhwan.anyang_yi.network.Result
import com.juhwan.anyang_yi.network.ResultList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class ItemDataSource : PageKeyedDataSource<Int, ResultList>() {

    //private val pageSize = 15
    private val firstPage = 1
    private val parameter: MutableMap<String, String> = HashMap()

    init {
        parameter["menuId"] = "23"
        parameter["bsIdx"] = "61"
        parameter["bcIdx"] = "0"
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ResultList>
    ) {
        parameter["page"] = firstPage.toString()
        val call = NoticeNetwork.getJsonApi().boardListPost(parameter)

        call.enqueue(object : Callback<Result> {
            override fun onResponse(
                call: Call<Result>,
                response: Response<Result>
            ) {
                if(response.isSuccessful) {
                    try {
                        callback.onResult(response.body()!!.resultList, null, firstPage+1)
                        Log.v("뭐들어있는데", response.body()!!.resultList[0].SUBJECT.toString())
                    } catch (e: Exception) {

                    }
                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {

            }
        })
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ResultList>
    ) {
        parameter["page"] = params.key.toString()
        val call = NoticeNetwork.getJsonApi().boardListPost(parameter)

        call.enqueue(object : Callback<Result> {
            override fun onResponse(
                call: Call<Result>,
                response: Response<Result>
            ) {
                if(response.isSuccessful) {
                    try {
                        callback.onResult(response.body()!!.resultList.toMutableList(), params.key+1)
                    } catch (e: Exception) {

                    }
                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {

            }
        })
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, ResultList>
    ) {
        // No need to implement code if you just scroll down
    }
}