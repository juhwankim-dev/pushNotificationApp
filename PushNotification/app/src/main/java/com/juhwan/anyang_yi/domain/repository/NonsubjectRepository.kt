package com.juhwan.anyang_yi.domain.repository

import com.juhwan.anyang_yi.domain.model.Nonsubject
import com.juhwan.anyang_yi.present.utils.Result

interface NonsubjectRepository {
    fun getNonsubjectNoticeList(): Result<List<Nonsubject>>
    fun getRecentNonsubjectNoticeList(): Result<List<Nonsubject>>
}