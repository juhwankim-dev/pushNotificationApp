package com.juhwan.anyang_yi.domain.repository

import com.juhwan.anyang_yi.domain.model.Univ
import com.juhwan.anyang_yi.present.utils.Result

interface UnivRepository {
    fun getUnivNoticeList(): Result<List<Univ>>
}