package com.juhwan.anyang_yi.data.api

import com.juhwan.anyang_yi.data.model.KakaoEntity
import retrofit2.Response
import retrofit2.http.GET

interface KakaoApi {
    @GET("_jxehRd")
    suspend fun getEduNoticeList(): Response<KakaoEntity>

    @GET("_iMxaFC")
    suspend fun getJobNoticeList(): Response<KakaoEntity>

    @GET("_lNmNd")
    suspend fun getAriPanelNoticeList(): Response<KakaoEntity>
}