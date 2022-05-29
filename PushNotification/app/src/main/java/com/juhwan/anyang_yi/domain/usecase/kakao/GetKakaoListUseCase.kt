package com.juhwan.anyang_yi.domain.usecase.kakao

import com.juhwan.anyang_yi.domain.model.Kakao
import com.juhwan.anyang_yi.domain.model.Univ
import com.juhwan.anyang_yi.domain.repository.KakaoRepository
import com.juhwan.anyang_yi.present.utils.Result
import javax.inject.Inject

class GetKakaoListUseCase @Inject constructor(
    private val kakaoRepository: KakaoRepository
) {
    fun getEduNoticeList(): Result<List<Kakao>> = kakaoRepository.getEduNoticeList()
    fun getJobNoticeList(): Result<List<Kakao>> = kakaoRepository.getJobNoticeList()
    fun getAriPanelNoticeList(): Result<List<Kakao>> = kakaoRepository.getAriPanelNoticeList()
}