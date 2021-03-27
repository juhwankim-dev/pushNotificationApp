package com.juhwan.anyang_yi.network

import com.juhwan.anyang_yi.data.KakaoNotice
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface KakaoNoticeApiService {

    @GET("_jxehRd")
    fun getEduNotice(): Call<KakaoNotice>

    @GET("_iMxaFC")
    fun getJobNotice(): Call<KakaoNotice>

    @GET("_lNmNd")
    fun getAriPanelNotice(): Call<KakaoNotice>
}

object KakaoNoticeApi {
    private const val baseUrl = "https://pf-wapi.kakao.com/web/profiles/" // 베이스 URL

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun createApi(): KakaoNoticeApiService {
        return retrofit.create(KakaoNoticeApiService::class.java)
    }
}