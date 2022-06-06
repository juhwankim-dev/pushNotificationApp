package com.juhwan.anyang_yi.domain.usecase.kakao

import com.juhwan.anyang_yi.domain.model.Kakao
import com.juhwan.anyang_yi.domain.repository.KakaoRepository
import com.juhwan.anyang_yi.present.utils.Result
import javax.inject.Inject

class GetKakaoListUseCase @Inject constructor(
    private val kakaoRepository: KakaoRepository
) {
    suspend fun getEduNoticeList(): Result<List<Kakao>> = kakaoRepository.getEduNoticeList()
    suspend fun getJobNoticeList(): Result<List<Kakao>> = kakaoRepository.getJobNoticeList()
    suspend fun getAriPanelNoticeList(): Result<List<Kakao>> = kakaoRepository.getAriPanelNoticeList()
}