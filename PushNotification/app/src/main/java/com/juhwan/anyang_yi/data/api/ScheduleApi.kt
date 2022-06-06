package com.juhwan.anyang_yi.data.api

import com.juhwan.anyang_yi.data.model.ScheduleEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ScheduleApi {
    @GET("academic-schedule.do?")
    suspend fun getScheduleList(
        @Query("mode") mode: String,
        @Query("start") start: String,
        @Query("end") end: String
    ): Response<ScheduleEntity>
}