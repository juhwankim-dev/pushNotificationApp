package com.juhwan.anyang_yi.domain.usecase.univ

import androidx.paging.PagingData
import com.juhwan.anyang_yi.domain.model.Univ
import com.juhwan.anyang_yi.domain.repository.UnivRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchUnivListUseCase @Inject constructor(
    private val univRepository: UnivRepository
) {
    operator fun invoke(keyword: String): Flow<PagingData<Univ>> = univRepository.getSearchResultList(keyword)
}