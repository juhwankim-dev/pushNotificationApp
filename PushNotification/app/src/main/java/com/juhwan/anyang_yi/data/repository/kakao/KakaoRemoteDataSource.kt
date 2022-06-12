package com.juhwan.anyang_yi.data.repository.kakao

import com.juhwan.anyang_yi.data.model.KakaoEntity
import retrofit2.Response
import retrofit2.http.GET

interface KakaoRemoteDataSource {
    suspend fun getEduNoticeList(): Response<KakaoEntity>
    suspend fun getJobNoticeList(): Response<KakaoEntity>
    suspend fun getAriPanelNoticeList(): Response<KakaoEntity>
}