package com.juhwan.anyang_yi.data.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ContactApi {

    @GET("contents.do?ciIdx=42&menuId=69")
    fun boardListPost(): Call<ResponseBody>
}

object ContactNetwork {
    private const val baseUrl = "http://www.anyang.ac.kr/contents/" // 베이스 URL

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getJsonApi(): ContactApi {
        return retrofit.create(ContactApi::class.java)
    }
}