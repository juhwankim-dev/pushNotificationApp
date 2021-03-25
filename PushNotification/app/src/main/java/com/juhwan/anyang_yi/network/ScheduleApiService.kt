package com.juhwan.anyang_yi.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface ScheduleApiService {

    @Headers("Accept-Language: ko, en-US")
    @GET("search?q=cache:https://sub.anyang.ac.kr/AYUhub_web/support/collegeInfo/coll0101.asp")
    fun getSchedule(): Call<ResponseBody>
}

object ScheduleApi {
    private const val baseUrl = "https://webcache.googleusercontent.com/" // 베이스 URL

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun createApi(): ScheduleApiService {
        return retrofit.create(ScheduleApiService::class.java)
    }
}