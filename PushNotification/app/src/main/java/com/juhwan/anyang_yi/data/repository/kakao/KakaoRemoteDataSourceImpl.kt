package com.juhwan.anyang_yi.data.repository.kakao

import com.juhwan.anyang_yi.data.api.KakaoApi
import com.juhwan.anyang_yi.data.model.KakaoEntity
import retrofit2.Response
import javax.inject.Inject

class KakaoRemoteDataSourceImpl @Inject constructor(
    private val kakaoApi: KakaoApi
) : KakaoRemoteDataSource {
    override fun getEduNoticeList(): Response<KakaoEntity> {
        return kakaoApi.getEduNoticeList()
    }

    override fun getJobNoticeList(): Response<KakaoEntity> {
        return kakaoApi.getJobNoticeList()
    }

    override fun getAriPanelNoticeList(): Response<KakaoEntity> {
        return kakaoApi.getAriPanelNoticeList()
    }
}