package com.juhwan.anyang_yi.domain.usecase.keyword

import androidx.lifecycle.LiveData
import com.juhwan.anyang_yi.data.model.KeywordEntity
import com.juhwan.anyang_yi.domain.repository.KeywordRepository
import javax.inject.Inject

class ReadKeywordListUseCase @Inject constructor(
    private val keywordRepository: KeywordRepository
) {
    operator fun invoke(): LiveData<List<KeywordEntity>>
            = keywordRepository.readKeywordList()
}