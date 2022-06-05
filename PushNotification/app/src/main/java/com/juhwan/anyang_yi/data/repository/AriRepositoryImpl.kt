package com.juhwan.anyang_yi.data.repository

import com.juhwan.anyang_yi.data.mapper.AriMapper
import com.juhwan.anyang_yi.data.repository.ari.AriRemoteDataSource
import com.juhwan.anyang_yi.domain.model.Ari
import com.juhwan.anyang_yi.domain.repository.AriRepository
import com.juhwan.anyang_yi.present.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AriRepositoryImpl @Inject constructor(
    private val ariRemoteDataSource: AriRemoteDataSource
) : AriRepository {

    override fun getAriNoticeList(page: Int): Result<List<Ari>> {
        val field: MutableMap<String, String> = HashMap()
        field["schWord"] = "empty"
        field["noneChk"] = "2"
        field["bbsidx"] = "21"
        field["pageNo"] = page.toString()

        return try {
            val response = withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                ariRemoteDataSource.getAriNoticeList(field)
            }

            if(response.isSuccessful && response.body() != null) {
                Result.success(AriMapper(response.body()!!))
            } else {
                Result.error(response.errorBody().toString(), null)
            }
        } catch (error: Exception) {
            Result.fail()
        }
    }

    override fun getRecentAriNoticeList(): Result<List<Ari>> {
        val result = withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
            getAriNoticeList(1)
        }

        return result.apply {
            if(this.data != null && this.data!!.size > 5) {
                this.data!!.subList(0, 5)
            }
        }
    }
}