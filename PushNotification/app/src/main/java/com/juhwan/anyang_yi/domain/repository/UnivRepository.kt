package com.juhwan.anyang_yi.domain.repository

import androidx.paging.PagingData
import com.juhwan.anyang_yi.domain.model.Univ
import com.juhwan.anyang_yi.present.utils.Result
import kotlinx.coroutines.flow.Flow

interface UnivRepository {
    fun getUnivNoticeList(categoryId: String?): Flow<PagingData<Univ>>
    suspend fun getRecentUnivNoticeList(): Result<List<Univ>>
    suspend fun getSearchResultList(keyword: String, offset: Int): Result<List<Univ>>
}