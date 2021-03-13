package com.juhwan.anyang_yi.ui.schedule

import androidx.lifecycle.ViewModel
import com.juhwan.anyang_yi.repository.ScheduleRepository

class ScheduleViewModel: ViewModel() {
    private val repository = ScheduleRepository()

    fun requestSchedule(): List<Schedule> {
        return repository.requestSchedule()
    }
}