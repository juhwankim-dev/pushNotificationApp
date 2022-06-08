package com.juhwan.anyang_yi.domain.usecase.univ

import com.juhwan.anyang_yi.domain.model.Univ
import com.juhwan.anyang_yi.domain.repository.UnivRepository
import com.juhwan.anyang_yi.present.utils.Result
import javax.inject.Inject

class GetSearchResultListUseCase @Inject constructor(
    private val univRepository: UnivRepository
) {
    suspend operator fun invoke(keyword: String, offset: Int): Result<List<Univ>> = univRepository.getSearchResultList(keyword, offset)
}