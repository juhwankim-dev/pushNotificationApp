package com.juhwan.anyang_yi.present.views.home.notice.univ

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juhwan.anyang_yi.domain.model.Univ
import com.juhwan.anyang_yi.domain.usecase.univ.GetUnivListUseCase
import com.juhwan.anyang_yi.present.utils.Result
import com.juhwan.anyang_yi.present.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UnivViewModel @Inject constructor(
    private val getUnivListUseCase: GetUnivListUseCase
): ViewModel() {

    private val _UnivNoticeList = MutableLiveData<List<Univ>>()
    val univNoticeList: LiveData<List<Univ>> get() = _UnivNoticeList

    private val _problem = MutableLiveData<Result<Any>>()
    val problem: LiveData<Result<Any>> get() = _problem

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