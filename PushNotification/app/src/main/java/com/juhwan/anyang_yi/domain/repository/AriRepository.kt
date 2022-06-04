package com.juhwan.anyang_yi.domain.repository

import com.juhwan.anyang_yi.domain.model.Ari
import com.juhwan.anyang_yi.present.utils.Result

interface AriRepository {
    fun getAriNoticeList(page: Int): Result<List<Ari>>
    fun getRecentAriNoticeList(): Result<List<Ari>>
}