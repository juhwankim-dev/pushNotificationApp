package com.juhwan.anyang_yi.data.repository.schedule

import com.juhwan.anyang_yi.data.model.ScheduleEntity
import retrofit2.Response

interface ScheduleRemoteDataSource {
    suspend fun getScheduleList(mode: String, start: String, end: String): Response<ScheduleEntity>
}