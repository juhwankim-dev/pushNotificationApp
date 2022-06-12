package com.juhwan.anyang_yi.present.views.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juhwan.anyang_yi.domain.model.Schedule
import com.juhwan.anyang_yi.domain.usecase.schedule.GetScheduleListUseCase
import com.juhwan.anyang_yi.present.utils.Result
import com.juhwan.anyang_yi.present.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    private val getScheduleListUseCase: GetScheduleListUseCase
): ViewModel() {
    private val _scheduleList = MutableLiveData<List<Schedule>>()
    val scheduleList: LiveData<List<Schedule>> get() = _scheduleList

    private val _problem = MutableLiveData<Result<Any>>()
    val problem: LiveData<Result<Any>> get() = _problem

    fun getScheduleList(start: String, end: String) {
        viewModelScope.launch {
            val result = getScheduleListUseCase(start, end)
            if(result.status == Status.SUCCESS) {
                result.data.let { _scheduleList.postValue(it) }
            } else {
                _problem.postValue(result)
            }
        }
    }
}