package com.juhwan.anyang_yi.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MainNoticeApi {

    @FormUrlEncoded
    @POST("boardList.do")
    fun boardListPost(@FieldMap fields: MutableMap<String, String>): Call<Result>
}

object MainNoticeNetwork {
    private val baseUrl = "http://www.anyang.ac.kr/bbs/ajax/" // 베이스 URL

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getJsonApi(): MainNoticeApi {
        return retrofit.create(MainNoticeApi::class.java)
    }
}