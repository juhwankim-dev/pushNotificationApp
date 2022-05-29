package com.juhwan.anyang_yi.data.repository.kakao

import com.juhwan.anyang_yi.data.model.KakaoEntity
import retrofit2.Response
import retrofit2.http.GET

interface KakaoRemoteDataSource {
    fun getEduNoticeList(): Response<KakaoEntity>
    fun getJobNoticeList(): Response<KakaoEntity>
    fun getAriPanelNoticeList(): Response<KakaoEntity>
}