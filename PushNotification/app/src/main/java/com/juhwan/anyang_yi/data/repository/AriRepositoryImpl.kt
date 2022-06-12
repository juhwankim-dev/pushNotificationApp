package com.juhwan.anyang_yi.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.juhwan.anyang_yi.data.api.AriApi
import com.juhwan.anyang_yi.data.mapper.AriMapper
import com.juhwan.anyang_yi.data.paging.AriPagingDataSource
import com.juhwan.anyang_yi.data.repository.ari.AriRemoteDataSource
import com.juhwan.anyang_yi.domain.model.Ari
import com.juhwan.anyang_yi.domain.repository.AriRepository
import com.juhwan.anyang_yi.present.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AriRepositoryImpl @Inject constructor(
    private val ariApi: AriApi,
    private val ariRemoteDataSource: AriRemoteDataSource
) : AriRepository {

    override fun getAriNoticeList(): Flow<PagingData<Ari>> {
        return Pager(
            config = PagingConfig(
                pageSize = ARI_NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { AriPagingDataSource(ariApi) }
        ).flow
    }

    override suspend fun getRecentAriNoticeList(): Result<List<Ari>> {
        return try {
            val field: MutableMap<String, String> = HashMap()
            field["schWord"] = "empty"
            field["noneChk"] = "2"
            field["bbsidx"] = "21"
            field["pageNo"] = "1"

            val response = withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                ariRemoteDataSource.getAriNoticeList(field)
            }

            if(response.isSuccessful && response.body() != null) {
                var data = AriMapper(response.body()!!)
                if(data.size > 5) {
                    data = data.subList(0, 5)
                }
                Result.success(data)
            } else {
                Result.error(response.errorBody().toString(), null)
            }
        } catch (error: Exception) {
            Result.fail()
        }
    }

    companion object {
        const val ARI_NETWORK_PAGE_SIZE = 10
    }
}