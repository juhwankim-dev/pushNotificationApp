package com.juhwan.anyang_yi.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.juhwan.anyang_yi.data.api.UnivApi
import com.juhwan.anyang_yi.data.mapper.UnivMapper
import com.juhwan.anyang_yi.data.paging.SearchUnivPagingDataSource
import com.juhwan.anyang_yi.data.paging.UnivPagingDataSource
import com.juhwan.anyang_yi.data.repository.univ.UnivRemoteDataSource
import com.juhwan.anyang_yi.domain.model.Univ
import com.juhwan.anyang_yi.domain.repository.UnivRepository
import com.juhwan.anyang_yi.present.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UnivRepositoryImpl @Inject constructor(
    private val univApi: UnivApi,
    private val univRemoteDataSource: UnivRemoteDataSource
) : UnivRepository {

    override fun getUnivNoticeList(categoryId: String?): Flow<PagingData<Univ>> {
        return Pager(
            config = PagingConfig(
                pageSize = UNIV_NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { UnivPagingDataSource(univApi, categoryId) }
        ).flow
    }

    override suspend fun getRecentUnivNoticeList(): Result<List<Univ>> {
        return try {
            val response = withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                univRemoteDataSource.getRecentUnivNoticeList()
            }

            if (response.isSuccessful && response.body() != null) {
                var data = UnivMapper(response.body()!!)
                if(data.size > 5) {
                    data = data.subList(0, 5)
                }
                Result.success(data)
            } else {
                Result.error(response.message().toString(), null)
            }
        } catch (error: Exception) {
            Result.fail()
        }
    }

    override suspend fun hasSearchResult(keyword: String): Result<Boolean> {
        return try {
            val response = withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                univRemoteDataSource.getSearchResultList(keyword, "0")
            }

            if(response.isSuccessful && response.body() != null) {
                Result.success(UnivMapper(response.body()!!).isNotEmpty())
            } else {
                Result.error(response.errorBody().toString(), null)
            }
        } catch (error: Exception) {
            Result.fail()
        }
    }

    override fun getSearchResultList(keyword: String): Flow<PagingData<Univ>> {
        return Pager(
            config = PagingConfig(
                pageSize = UNIV_NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { SearchUnivPagingDataSource(univApi, keyword) }
        ).flow
    }

    companion object {
        const val UNIV_NETWORK_PAGE_SIZE = 10
    }
}