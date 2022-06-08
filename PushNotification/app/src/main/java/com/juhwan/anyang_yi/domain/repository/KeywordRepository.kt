package com.juhwan.anyang_yi.domain.repository

import androidx.lifecycle.LiveData
import com.juhwan.anyang_yi.data.model.KeywordEntity
import com.juhwan.anyang_yi.present.utils.Result

interface KeywordRepository {
    suspend fun deleteKeyword(keyword: String)
    fun readKeywordList(): LiveData<List<KeywordEntity>>
    suspend fun writeKeyword(keyword: String): Result<Any>
}