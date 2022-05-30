package com.juhwan.anyang_yi.present.views.home.notice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juhwan.anyang_yi.domain.model.Ari
import com.juhwan.anyang_yi.domain.model.Nonsubject
import com.juhwan.anyang_yi.domain.model.Univ
import com.juhwan.anyang_yi.domain.usecase.ari.GetAriListUseCase
import com.juhwan.anyang_yi.domain.usecase.nonsubject.GetNonsubjectListUseCase
import com.juhwan.anyang_yi.domain.usecase.univ.GetUnivListUseCase
import com.juhwan.anyang_yi.present.utils.Result
import com.juhwan.anyang_yi.present.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoticeViewModel @Inject constructor(
    private val getAriListUseCase: GetAriListUseCase,
    private val getNonsubjectListUseCase: GetNonsubjectListUseCase,
    private val getUnivListUseCase: GetUnivListUseCase
): ViewModel() {
    private val _AriNoticeList = MutableLiveData<List<Ari>>()
    val ariNoticeList: LiveData<List<Ari>> get() = _AriNoticeList

    private val _NonsubjectNoticeList = MutableLiveData<List<Nonsubject>>()
    val nonsubjectNoticeList: LiveData<List<Nonsubject>> get() = _NonsubjectNoticeList

    private val _UnivNoticeList = MutableLiveData<List<Univ>>()
    val univNoticeList: LiveData<List<Univ>> get() = _UnivNoticeList

    private val _problem = MutableLiveData<Result<Any>>()
    val problem: LiveData<Result<Any>> get() = _problem

    fun getAriNoticeList(page: Int) {
        viewModelScope.launch {
            val result = getAriListUseCase(page)
            if(result.status == Status.SUCCESS) {
                result.data.let { _AriNoticeList.postValue(it) }
            } else {
                _problem.postValue(result)
            }
        }
    }

    fun getNonsubjectNoticeList() {
        viewModelScope.launch {
            val result = getNonsubjectListUseCase()
            if(result.status == Status.SUCCESS) {
                result.data.let { _NonsubjectNoticeList.postValue(it) }
            } else {
                _problem.postValue(result)
            }
        }
    }

    fun getUnivNoticeList() {
        viewModelScope.launch {
            val result = getUnivListUseCase()
            if(result.status == Status.SUCCESS) {
                result.data.let { _UnivNoticeList.postValue(it) }
            } else {
                _problem.postValue(result)
            }
        }
    }
}