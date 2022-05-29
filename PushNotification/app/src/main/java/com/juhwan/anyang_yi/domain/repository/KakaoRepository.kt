package com.juhwan.anyang_yi.domain.repository

import com.juhwan.anyang_yi.domain.model.Kakao
import com.juhwan.anyang_yi.present.utils.Result

interface KakaoRepository {
    fun getEduNoticeList(): Result<List<Kakao>>
    fun getJobNoticeList(): Result<List<Kakao>>
    fun getAriPanelNoticeList(): Result<List<Kakao>>
}