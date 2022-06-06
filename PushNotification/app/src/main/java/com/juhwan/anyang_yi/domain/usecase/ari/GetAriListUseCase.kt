package com.juhwan.anyang_yi.domain.usecase.ari

import com.juhwan.anyang_yi.domain.model.Ari
import com.juhwan.anyang_yi.domain.repository.AriRepository
import com.juhwan.anyang_yi.present.utils.Result
import javax.inject.Inject

class GetAriListUseCase @Inject constructor(
    private val ariRepository: AriRepository
) {
    suspend operator fun invoke(page: Int): Result<List<Ari>> = ariRepository.getAriNoticeList(page)
}