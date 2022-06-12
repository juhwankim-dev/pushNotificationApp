package com.juhwan.anyang_yi.present.views.home.notice.nonsubject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juhwan.anyang_yi.domain.model.Nonsubject
import com.juhwan.anyang_yi.domain.usecase.nonsubject.GetNonsubjectListUseCase
import com.juhwan.anyang_yi.present.utils.Result
import com.juhwan.anyang_yi.present.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NonsubjectViewModel @Inject constructor(
    private val getNonsubjectListUseCase: GetNonsubjectListUseCase,
): ViewModel() {

    private val _nonsubjectNoticeList = MutableLiveData<List<Nonsubject>>()
    val nonsubjectNoticeList: LiveData<List<Nonsubject>> get() = _nonsubjectNoticeList

    private val _problem = MutableLiveData<Result<Any>>()
    val problem: LiveData<Result<Any>> get() = _problem

    fun getNonsubjectNoticeList() {
        viewModelScope.launch {
            val result = getNonsubjectListUseCase()
            if(result.status == Status.SUCCESS) {
                result.data.let { _nonsubjectNoticeList.postValue(it) }
            } else {
                _problem.postValue(result)
            }
        }
    }
}