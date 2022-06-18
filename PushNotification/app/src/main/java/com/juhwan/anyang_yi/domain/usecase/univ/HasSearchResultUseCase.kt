package com.juhwan.anyang_yi.domain.usecase.univ

import com.juhwan.anyang_yi.domain.repository.UnivRepository
import com.juhwan.anyang_yi.present.utils.Result
import javax.inject.Inject

class HasSearchResultUseCase @Inject constructor(
    private val univRepository: UnivRepository
) {
    suspend operator fun invoke(keyword: String): Result<Boolean> = univRepository.hasSearchResult(keyword)
}