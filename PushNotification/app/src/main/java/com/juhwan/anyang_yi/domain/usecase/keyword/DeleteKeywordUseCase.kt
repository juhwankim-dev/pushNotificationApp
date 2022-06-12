package com.juhwan.anyang_yi.domain.usecase.keyword

import com.juhwan.anyang_yi.domain.repository.KeywordRepository
import javax.inject.Inject

class DeleteKeywordUseCase @Inject constructor(
    private val keywordRepository: KeywordRepository
) {
    suspend operator fun invoke(keyword: String) = keywordRepository.deleteKeyword(keyword)
}