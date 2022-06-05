package com.juhwan.anyang_yi.present.views.home.keyword

import android.app.Application
import androidx.lifecycle.*
import com.juhwan.anyang_yi.data.db.Keyword
import com.juhwan.anyang_yi.data.repository.KeywordRepository
import com.juhwan.anyang_yi.domain.model.Ari
import com.juhwan.anyang_yi.domain.usecase.ari.GetAriListUseCase
import com.juhwan.anyang_yi.domain.usecase.nonsubject.GetNonsubjectListUseCase
import com.juhwan.anyang_yi.domain.usecase.univ.GetUnivListUseCase
import com.juhwan.anyang_yi.present.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO: searchUsecase로 만들기, keywordUsecase 만들기
@HiltViewModel
class KeywordViewModel @Inject constructor(
    private val getUnivListUseCase: GetUnivListUseCase
): ViewModel() {
    private val _ariNoticeList = MutableLiveData<List<Ari>>()
    val ariNoticeList: LiveData<List<Ari>> get() = _ariNoticeList

    private val _problem = MutableLiveData<com.juhwan.anyang_yi.present.utils.Result<Any>>()
    val problem: LiveData<com.juhwan.anyang_yi.present.utils.Result<Any>> get() = _problem

    fun getAriNoticeList(page: Int) {
//        viewModelScope.launch {
//            val result = getAriListUseCase(page)
//            if(result.status == Status.SUCCESS) {
//                result.data.let { _ariNoticeList.postValue(it) }
//            } else {
//                _problem.postValue(result)
//            }
//        }
    }

//    private val items = repository.getAll()
//
//    private val mainNoticeRepository = MainNoticeRepository()
//    private val searchResult: LiveData<Result>
//        get() = mainNoticeRepository._searchResult
//
//    fun insert(keyword: Keyword) {
//        repository.insert(keyword)
//    }
//
//    fun deleteKeywordByTitle(keyword: String) {
//        repository.deleteKeywordByTitle(keyword)
//    }
//
//    fun getAll(): LiveData<List<Keyword>> {
//        return items
//    }
//
//    fun searchKeyword(keyword: String){
//        mainNoticeRepository.searchKeyword(keyword)
//    }
//
//    fun getResult(): LiveData<Result> {
//        return searchResult
//    }
}