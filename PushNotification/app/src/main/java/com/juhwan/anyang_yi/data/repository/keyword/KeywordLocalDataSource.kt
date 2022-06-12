package com.juhwan.anyang_yi.data.repository.keyword

import androidx.lifecycle.LiveData
import com.juhwan.anyang_yi.data.model.KeywordEntity

interface KeywordLocalDataSource {
    suspend fun deleteKeyword(keyword: String)
    fun readKeywordList(): LiveData<List<KeywordEntity>>
    suspend fun writeKeyword(keywordEntity: KeywordEntity)
}