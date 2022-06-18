package com.juhwan.anyang_yi.domain.usecase.ari

import androidx.paging.PagingData
import com.juhwan.anyang_yi.domain.model.Ari
import com.juhwan.anyang_yi.domain.repository.AriRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchAriListUseCase @Inject constructor(
    private val ariRepository: AriRepository
) {
    operator fun invoke(keyword: String): Flow<PagingData<Ari>> = ariRepository.getSearchAriNoticeList(keyword)
}