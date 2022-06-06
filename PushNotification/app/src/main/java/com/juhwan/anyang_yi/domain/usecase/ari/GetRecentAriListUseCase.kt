package com.juhwan.anyang_yi.domain.usecase.ari

import com.juhwan.anyang_yi.domain.model.Ari
import com.juhwan.anyang_yi.domain.repository.AriRepository
import com.juhwan.anyang_yi.present.utils.Result
import javax.inject.Inject

class GetRecentAriListUseCase @Inject constructor(
    private val ariRepository: AriRepository
) {
    suspend operator fun invoke(): Result<List<Ari>> = ariRepository.getRecentAriNoticeList()
}