package com.juhwan.anyang_yi.domain.repository

import com.juhwan.anyang_yi.domain.model.Kakao
import com.juhwan.anyang_yi.present.utils.Result

interface KakaoRepository {
    suspend fun getEduNoticeList(): Result<List<Kakao>>
    suspend fun getJobNoticeList(): Result<List<Kakao>>
    suspend fun getAriPanelNoticeList(): Result<List<Kakao>>
}