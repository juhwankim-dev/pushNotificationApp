package com.juhwan.anyang_yi.present.views.home.social

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juhwan.anyang_yi.domain.model.Kakao
import com.juhwan.anyang_yi.domain.usecase.kakao.GetKakaoListUseCase
import com.juhwan.anyang_yi.present.utils.Result
import com.juhwan.anyang_yi.present.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SocialViewModel @Inject constructor(
    private val getKakaoListUseCase: GetKakaoListUseCase
): ViewModel() {
    private val _eduNoticeList = MutableLiveData<List<Kakao>>()
    val eduNoticeList: LiveData<List<Kakao>> get() = _eduNoticeList

    private val _jobNoticeList = MutableLiveData<List<Kakao>>()
    val jobNoticeList: LiveData<List<Kakao>> get() = _jobNoticeList

    private val _ariPanelNoticeList = MutableLiveData<List<Kakao>>()
    val ariPanelNoticeList: LiveData<List<Kakao>> get() = _ariPanelNoticeList

    private val _problem = MutableLiveData<Result<Any>>()
    val problem: LiveData<Result<Any>> get() = _problem

    fun getEduNoticeList() {
        viewModelScope.launch {
            val result = getKakaoListUseCase.getEduNoticeList()
            if(result.status == Status.SUCCESS) {
                result.data.let { _eduNoticeList.postValue(it) }
            } else {
                _problem.postValue(result)
            }
        }
    }

    fun getJobNoticeList() {
        viewModelScope.launch {
            val result = getKakaoListUseCase.getJobNoticeList()
            if(result.status == Status.SUCCESS) {
                result.data.let { _jobNoticeList.postValue(it) }
            } else {
                _problem.postValue(result)
            }
        }
    }

    fun getAriPanelNoticeList() {
        viewModelScope.launch {
            val result = getKakaoListUseCase.getAriPanelNoticeList()
            if(result.status == Status.SUCCESS) {
                result.data.let { _ariPanelNoticeList.postValue(it) }
            } else {
                _problem.postValue(result)
            }
        }
    }
}