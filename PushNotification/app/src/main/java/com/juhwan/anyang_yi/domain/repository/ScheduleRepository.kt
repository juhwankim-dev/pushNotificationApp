package com.juhwan.anyang_yi.domain.repository

import com.juhwan.anyang_yi.domain.model.Schedule
import com.juhwan.anyang_yi.present.utils.Result

interface ScheduleRepository {
    fun getScheduleList(): Result<List<Schedule>>
}