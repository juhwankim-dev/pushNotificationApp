package com.juhwan.anyang_yi.domain.repository

import com.juhwan.anyang_yi.domain.model.Schedule
import com.juhwan.anyang_yi.present.utils.Result

interface ScheduleRepository {
    suspend fun getScheduleList(start: String, end: String): Result<List<Schedule>>
}