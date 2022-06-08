package com.juhwan.anyang_yi.data.repository

import androidx.lifecycle.LiveData
import com.juhwan.anyang_yi.data.mapper.KeywordMapper
import com.juhwan.anyang_yi.data.model.KeywordEntity
import com.juhwan.anyang_yi.data.repository.keyword.KeywordLocalDataSource
import com.juhwan.anyang_yi.domain.repository.KeywordRepository
import com.juhwan.anyang_yi.present.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class KeywordRepositoryImpl @Inject constructor(
    private val keywordLocalDataSource: KeywordLocalDataSource
): KeywordRepository {
    override suspend fun deleteKeyword(keyword: String) {
        keywordLocalDataSource.deleteKeyword(keyword)
    }

    override fun readKeywordList(): LiveData<List<KeywordEntity>> {
        return keywordLocalDataSource.readKeywordList()
    }

    override suspend fun writeKeyword(keyword: String): Result<Any> {
        return try {
            withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                keywordLocalDataSource.writeKeyword(KeywordMapper.mapperToKeywordEntity(keyword))
            }

            Result.success(null)
        } catch (error: Exception) {
            Result.error(error.message.toString(), null)
        }
    }
}