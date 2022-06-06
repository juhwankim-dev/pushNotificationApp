package com.juhwan.anyang_yi.data.repository

import com.juhwan.anyang_yi.data.mapper.KakaoMapper
import com.juhwan.anyang_yi.data.repository.kakao.KakaoRemoteDataSource
import com.juhwan.anyang_yi.domain.model.Kakao
import com.juhwan.anyang_yi.domain.repository.KakaoRepository
import com.juhwan.anyang_yi.present.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class KakaoRepositoryImpl @Inject constructor(
    private val kakaoRemoteDataSource: KakaoRemoteDataSource
) : KakaoRepository {

    override suspend fun getEduNoticeList(): Result<List<Kakao>> {
        return try {
            val response = withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                kakaoRemoteDataSource.getEduNoticeList()
            }

            if (response.isSuccessful && response.body() != null) {
                Result.success(KakaoMapper(response.body()!!))
            } else {
                Result.error(response.errorBody().toString(), null)
            }
        } catch (error: Exception) {
            Result.fail()
        }
    }

    override suspend fun getJobNoticeList(): Result<List<Kakao>> {
        return try {
            val response = withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                kakaoRemoteDataSource.getJobNoticeList()
            }

            if (response.isSuccessful && response.body() != null) {
                Result.success(KakaoMapper(response.body()!!))
            } else {
                Result.error(response.errorBody().toString(), null)
            }
        } catch (error: Exception) {
            Result.fail()
        }
    }

    override suspend fun getAriPanelNoticeList(): Result<List<Kakao>> {
        return try {
            val response = withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                kakaoRemoteDataSource.getAriPanelNoticeList()
            }

            if (response.isSuccessful && response.body() != null) {
                Result.success(KakaoMapper(response.body()!!))
            } else {
                Result.error(response.errorBody().toString(), null)
            }
        } catch (error: Exception) {
            Result.fail()
        }
    }
}