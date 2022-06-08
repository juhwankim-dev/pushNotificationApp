package com.juhwan.anyang_yi.data.repository.keyword

import androidx.lifecycle.LiveData
import com.juhwan.anyang_yi.data.db.KeywordDao
import com.juhwan.anyang_yi.data.model.KeywordEntity
import javax.inject.Inject

class KeywordLocalDataSourceImpl @Inject constructor(
    private val dao: KeywordDao
): KeywordLocalDataSource {
    suspend override fun deleteKeyword(keyword: String) {
        return dao.deleteKeyword(keyword)
    }

    override fun readKeywordList(): LiveData<List<KeywordEntity>> {
        return dao.readKeywordList()
    }

    suspend override fun writeKeyword(keywordEntity: KeywordEntity) {
        return dao.writeKeyword(keywordEntity)
    }
}