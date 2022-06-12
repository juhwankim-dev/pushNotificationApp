package com.juhwan.anyang_yi.domain.repository

import com.juhwan.anyang_yi.domain.model.Nonsubject
import com.juhwan.anyang_yi.present.utils.Result

interface NonsubjectRepository {
    suspend fun getNonsubjectNoticeList(): Result<List<Nonsubject>>
    suspend fun getRecentNonsubjectNoticeList(): Result<List<Nonsubject>>
}