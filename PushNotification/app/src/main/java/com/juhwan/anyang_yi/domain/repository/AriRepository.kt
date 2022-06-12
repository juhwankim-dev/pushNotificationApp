package com.juhwan.anyang_yi.domain.repository

import androidx.paging.PagingData
import com.juhwan.anyang_yi.domain.model.Ari
import com.juhwan.anyang_yi.present.utils.Result
import kotlinx.coroutines.flow.Flow

interface AriRepository {
    fun getAriNoticeList(): Flow<PagingData<Ari>>
    suspend fun getRecentAriNoticeList(): Result<List<Ari>>
}