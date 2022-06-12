package com.juhwan.anyang_yi.present.views.home.notice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juhwan.anyang_yi.domain.model.Ari
import com.juhwan.anyang_yi.domain.model.Nonsubject
import com.juhwan.anyang_yi.domain.model.Univ
import com.juhwan.anyang_yi.domain.usecase.ari.GetAriListUseCase
import com.juhwan.anyang_yi.domain.usecase.ari.GetRecentAriListUseCase
import com.juhwan.anyang_yi.domain.usecase.nonsubject.GetNonsubjectListUseCase
import com.juhwan.anyang_yi.domain.usecase.nonsubject.GetRecentNonsubjectListUseCase
import com.juhwan.anyang_yi.domain.usecase.univ.GetRecentUnivListUseCase
import com.juhwan.anyang_yi.domain.usecase.univ.GetUnivListUseCase
import com.juhwan.anyang_yi.present.utils.Result
import com.juhwan.anyang_yi.present.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoticeViewModel @Inject constructor(
    private val getRecentAriListUseCase: GetRecentAriListUseCase,
    private val getRecentNonsubjectListUseCase: GetRecentNonsubjectListUseCase,
    private val getRecentUnivListUseCase: GetRecentUnivListUseCase
): ViewModel() {
    private val _recentAriNoticeList = MutableLiveData<List<Ari>>()
    val recentAriNoticeList: LiveData<List<Ari>> get() = _recentAriNoticeList

    private val _recentNonsubjectNoticeList = MutableLiveData<List<Nonsubject>>()
    val recentNonsubjectNoticeList: LiveData<List<Nonsubject>> get() = _recentNonsubjectNoticeList

    private val _recentUnivNoticeList = MutableLiveData<List<Univ>>()
    val recentUnivNoticeList: LiveData<List<Univ>> get() = _recentUnivNoticeList

    private val _problem = MutableLiveData<Result<Any>>()
    val problem: LiveData<Result<Any>> get() = _problem

    fun getRecentAriNoticeList() {
        viewModelScope.launch {
            val result = getRecentAriListUseCase()
            if(result.status == Status.SUCCESS) {
                result.data.let { _recentAriNoticeList.postValue(it) }
            } else {
                _problem.postValue(result)
            }
        }
    }

    fun getRecentNonsubjectNoticeList() {
        viewModelScope.launch {
            val result = getRecentNonsubjectListUseCase()
            if(result.status == Status.SUCCESS) {
                result.data.let { _recentNonsubjectNoticeList.postValue(it) }
            } else {
                _problem.postValue(result)
            }
        }
    }

    fun getRecentUnivNoticeList() {
        viewModelScope.launch {
            val result = getRecentUnivListUseCase()
            if(result.status == Status.SUCCESS) {
                result.data.let { _recentUnivNoticeList.postValue(it) }
            } else {
                _problem.postValue(result)
            }
        }
    }
}