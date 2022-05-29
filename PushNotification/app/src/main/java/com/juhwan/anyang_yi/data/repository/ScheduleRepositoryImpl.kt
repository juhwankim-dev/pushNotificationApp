package com.juhwan.anyang_yi.data.repository

import com.juhwan.anyang_yi.data.mapper.ScheduleMapper
import com.juhwan.anyang_yi.data.repository.schedule.ScheduleRemoteDataSource
import com.juhwan.anyang_yi.domain.model.Schedule
import com.juhwan.anyang_yi.domain.repository.ScheduleRepository
import com.juhwan.anyang_yi.present.utils.Result
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleRemoteDataSource: ScheduleRemoteDataSource
) : ScheduleRepository {

    override fun getScheduleList(): Result<List<Schedule>> {
        return try {
            val response = scheduleRemoteDataSource.getScheduleList()

            if(response.isSuccessful && response.body() != null) {
                Result.success(ScheduleMapper(response.body()!!))
            } else {
                Result.error(response.errorBody().toString(), null)
            }
        } catch (error: Exception) {
            Result.fail()
        }
    }
}