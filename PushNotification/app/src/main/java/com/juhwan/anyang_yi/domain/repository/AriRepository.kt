package com.juhwan.anyang_yi.domain.repository

import com.juhwan.anyang_yi.domain.model.Ari
import com.juhwan.anyang_yi.present.utils.Result

interface AriRepository {
    suspend fun getAriNoticeList(page: Int): Result<List<Ari>>
    suspend fun getRecentAriNoticeList(): Result<List<Ari>>
}