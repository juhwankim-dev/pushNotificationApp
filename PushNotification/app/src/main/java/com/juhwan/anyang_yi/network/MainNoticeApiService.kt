package com.juhwan.anyang_yi.network

import com.juhwan.anyang_yi.data.Result
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MainNoticeApiService {

    @FormUrlEncoded
    @POST("boardList.do")
    fun getNotice(@FieldMap fields: MutableMap<String, String>): Call<Result>
}

object MainNoticeApi {
    private val baseUrl = "http://www.anyang.ac.kr/bbs/ajax/" // 베이스 URL

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun createApi(): MainNoticeApiService {
        return retrofit.create(MainNoticeApiService::class.java)
    }
}