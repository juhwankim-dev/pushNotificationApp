package com.juhwan.anyang_yi.data.repository

import android.util.Log
import com.juhwan.anyang_yi.data.mapper.ScheduleMapper
import com.juhwan.anyang_yi.data.repository.schedule.ScheduleRemoteDataSource
import com.juhwan.anyang_yi.domain.model.Schedule
import com.juhwan.anyang_yi.domain.repository.ScheduleRepository
import com.juhwan.anyang_yi.present.utils.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val scheduleRemoteDataSource: ScheduleRemoteDataSource
) : ScheduleRepository {

    override suspend fun getScheduleList(start: String, end: String): Result<List<Schedule>> {
        return try {
            val response = withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                scheduleRemoteDataSource.getScheduleList("getCalendarData", start, end)
            }

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