package com.juhwan.anyang_yi.data.repository.schedule

import com.juhwan.anyang_yi.data.api.ScheduleApi
import com.juhwan.anyang_yi.data.model.ScheduleEntity
import retrofit2.Response
import javax.inject.Inject

class ScheduleRemoteDataSourceImpl @Inject constructor(
    private val scheduleApi: ScheduleApi
) : ScheduleRemoteDataSource {

    override suspend fun getScheduleList(mode: String, start: String, end: String): Response<ScheduleEntity> {
        return scheduleApi.getScheduleList(mode, start, end)
    }
}