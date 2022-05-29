package com.juhwan.anyang_yi.domain.repository

import com.juhwan.anyang_yi.domain.model.Nonsubject
import com.juhwan.anyang_yi.present.utils.Result
import okhttp3.ResponseBody

interface NonsubjectRepository {
    fun getNonsubjectNoticeList(): Result<List<Nonsubject>>
}