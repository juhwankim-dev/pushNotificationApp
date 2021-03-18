package com.juhwan.anyang_yi.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AriApplyNoticeApi {
    @FormUrlEncoded
    @POST("indexProc.do")
    fun boardListPost(@FieldMap fields: MutableMap<String, String>): Call<ResponseBody>
}

object AriApplyNoticeNetwork {
    private const val baseUrl = "https://ari.anyang.ac.kr/user/subject/nsubject/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getJsonApi(): AriNoticeApi {
        return retrofit.create(AriNoticeApi::class.java)
    }
}