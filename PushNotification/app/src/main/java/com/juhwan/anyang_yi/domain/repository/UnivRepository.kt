package com.juhwan.anyang_yi.domain.repository

import com.juhwan.anyang_yi.domain.model.Univ
import com.juhwan.anyang_yi.present.utils.Result

interface UnivRepository {
    fun getUnivNoticeList(categoryId: String, offset: Int): Result<List<Univ>>
    fun getRecentUnivNoticeList(): Result<List<Univ>>
}