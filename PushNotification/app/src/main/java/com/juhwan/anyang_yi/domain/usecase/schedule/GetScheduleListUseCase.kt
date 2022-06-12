package com.juhwan.anyang_yi.domain.usecase.schedule

import com.juhwan.anyang_yi.domain.model.Schedule
import com.juhwan.anyang_yi.domain.repository.ScheduleRepository
import com.juhwan.anyang_yi.present.utils.Result
import javax.inject.Inject

class GetScheduleListUseCase @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) {
    suspend operator fun invoke(start: String, end: String): Result<List<Schedule>> = scheduleRepository.getScheduleList(start, end)
}