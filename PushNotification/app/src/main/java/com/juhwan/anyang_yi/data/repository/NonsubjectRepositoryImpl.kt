package com.juhwan.anyang_yi.data.repository

import com.juhwan.anyang_yi.data.mapper.NonsubjectMapper
import com.juhwan.anyang_yi.data.repository.nonsubject.NonsubjectRemoteDataSource
import com.juhwan.anyang_yi.domain.model.Nonsubject
import com.juhwan.anyang_yi.domain.repository.NonsubjectRepository
import com.juhwan.anyang_yi.present.utils.Result
import javax.inject.Inject

class NonsubjectRepositoryImpl @Inject constructor(
    private val nonsubjectRemoteDataSource: NonsubjectRemoteDataSource
) : NonsubjectRepository {

    override fun getNonsubjectNoticeList(): Result<List<Nonsubject>> {
        val field = hashMapOf("now" to "0")

        return try {
            val response = nonsubjectRemoteDataSource.getNonsubjectNoticeList(field)

            if(response.isSuccessful && response.body() != null) {
                Result.success(NonsubjectMapper(response.body()!!))
            } else {
                Result.error(response.errorBody().toString(), null)
            }
        } catch (error: Exception) {
            Result.fail()
        }
    }
}