package com.juhwan.anyang_yi.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.juhwan.anyang_yi.data.Result
import com.juhwan.anyang_yi.data.ResultList
import com.juhwan.anyang_yi.network.MainNoticeApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.HashMap

class MainNoticeRepository {
    private val parameterMainNotice: MutableMap<String, String> = HashMap()
    var _mainNotice = MutableLiveData<Result>()

    init {
        parameterMainNotice["menuId"] = "23"
        parameterMainNotice["bsIdx"] = "61"
        parameterMainNotice["bcIdx"] = "0"
    }

    fun loadMainNotice(page: Int) {
        parameterMainNotice["page"] = page.toString()

        val call = MainNoticeApi.createApi().getNotice(parameterMainNotice)

        call.enqueue(object : Callback<Result> {
            override fun onResponse(
                call: Call<Result>,
                response: Response<Result>
            ) {
                if (response.isSuccessful) {
                    var notice = ArrayList<ResultList>()
                    notice.addAll(response.body()!!.resultList)
                    notice.add(ResultList(" ", " ", " ", " ")) // 프로그레스바 위치할 자리

                    when (page) {
                        1 -> {
                            InitialRepository.mainNotice.addAll(notice)
                            InitialRepository.isFinished.value = InitialRepository.isFinished.value!!.plus(1)
                        }
                        else -> _mainNotice.value = Result(notice)
                    }
                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {

            }
        })
    }
}