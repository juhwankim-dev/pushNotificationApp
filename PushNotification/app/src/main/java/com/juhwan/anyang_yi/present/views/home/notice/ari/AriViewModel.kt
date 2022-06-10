package com.juhwan.anyang_yi.present.views.home.notice.ari

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juhwan.anyang_yi.domain.model.Ari
import com.juhwan.anyang_yi.domain.usecase.ari.GetAriListUseCase
import com.juhwan.anyang_yi.present.utils.Result
import com.juhwan.anyang_yi.present.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AriViewModel @Inject constructor(
    private val getAriListUseCase: GetAriListUseCase
): ViewModel() {
    private val _ariNoticeList = MutableLiveData<List<Ari>>()
    val ariNoticeList: LiveData<List<Ari>> get() = _ariNoticeList

    private val _problem = MutableLiveData<Result<Any>>()
    val problem: LiveData<Result<Any>> get() = _problem

    fun getAriNoticeList(page: Int) {
        viewModelScope.launch {
            val result = getAriListUseCase(page)
            if(result.status == Status.SUCCESS) {
                result.data.let { _ariNoticeList.postValue(it) }
            } else {
                _problem.postValue(result)
            }
        }
    }
}