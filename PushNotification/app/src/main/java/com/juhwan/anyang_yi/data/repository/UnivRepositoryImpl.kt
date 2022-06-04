package com.juhwan.anyang_yi.data.repository

import com.juhwan.anyang_yi.data.mapper.UnivMapper
import com.juhwan.anyang_yi.data.repository.univ.UnivRemoteDataSource
import com.juhwan.anyang_yi.domain.model.Univ
import com.juhwan.anyang_yi.domain.repository.UnivRepository
import com.juhwan.anyang_yi.present.utils.Result
import com.juhwan.anyang_yi.present.views.home.notice.univ.UnivActivity
import javax.inject.Inject

class UnivRepositoryImpl @Inject constructor(
    private val univRemoteDataSource: UnivRemoteDataSource
) : UnivRepository {
    override fun getUnivNoticeList(categoryId: String, offset: Int): Result<List<Univ>> {

        return try {
            val response = univRemoteDataSource.getUnivNoticeList(categoryId, offset.toString())

            if(response.isSuccessful && response.body() != null) {
                Result.success(UnivMapper(response.body()!!))
            } else {
                Result.error(response.errorBody().toString(), null)
            }
        } catch (error: Exception) {
            Result.fail()
        }
    }

    override fun getRecentUnivNoticeList(): Result<List<Univ>> {
        val result = getUnivNoticeList(UnivActivity.ALL, 0)
        return result.apply {
            if(this.data != null && this.data.size > 5) {
                this.data.subList(0, 5)
            }
        }
    }
}