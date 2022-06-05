package com.juhwan.anyang_yi.domain.usecase.univ

import com.juhwan.anyang_yi.domain.model.Univ
import com.juhwan.anyang_yi.domain.repository.UnivRepository
import com.juhwan.anyang_yi.present.utils.Result
import javax.inject.Inject

class GetUnivListUseCase @Inject constructor(
    private val univRepository: UnivRepository
) {
    suspend operator fun invoke(categoryId: String, offset: Int): Result<List<Univ>> = univRepository.getUnivNoticeList(categoryId, offset)
}