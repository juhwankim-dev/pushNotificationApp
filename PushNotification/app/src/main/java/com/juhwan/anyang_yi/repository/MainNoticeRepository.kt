package com.juhwan.anyang_yi.repository

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
    var _searchResult = MutableLiveData<Result>()

    init {
        parameterMainNotice["menuId"] = "23"
        parameterMainNotice["bsIdx"] = "61"
    }

    fun loadMainNotice(page: Int, bcIdx: String) {
        parameterMainNotice["page"] = page.toString()
        parameterMainNotice["bcIdx"] = bcIdx
        // bcIdx
        // 전체(0), 대학교(20), 학사(80), 학사(21), 취업정보(23), 입찰채용(24)

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
                    _mainNotice.value = Result(notice)
                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {

            }
        })
    }

    fun loadInitialMainNotice() {
        parameterMainNotice["page"] = "1"
        parameterMainNotice["bcIdx"] = "0"

        val call = MainNoticeApi.createApi().getNotice(parameterMainNotice)

        call.enqueue(object : Callback<Result> {
            override fun onResponse(
                call: Call<Result>,
                response: Response<Result>
            ) {
                if (response.isSuccessful) {
                    var notice = ArrayList<ResultList>()
                    notice.addAll(response.body()!!.resultList.subList(0, 5))

                    InitialRepository.mainNotice.addAll(notice)
                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {

            }
        })
    }

    fun searchKeyword(keyword: String){
        parameterMainNotice["page"] = "1"
        parameterMainNotice["searchCondition"] = "SUBJECT"
        parameterMainNotice["searchKeyword"] = keyword

        val call = MainNoticeApi.createApi().getNotice(parameterMainNotice)

        call.enqueue(object : Callback<Result> {
            override fun onResponse(
                call: Call<Result>,
                response: Response<Result>
            ) {
                if (response.isSuccessful) {
                    _searchResult.value = response.body()!!
                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {

            }
        })
    }
}