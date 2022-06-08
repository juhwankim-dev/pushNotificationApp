package com.juhwan.anyang_yi.present.views.home.keyword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juhwan.anyang_yi.data.model.KeywordEntity
import com.juhwan.anyang_yi.domain.model.Univ
import com.juhwan.anyang_yi.domain.usecase.keyword.DeleteKeywordUseCase
import com.juhwan.anyang_yi.domain.usecase.keyword.ReadKeywordListUseCase
import com.juhwan.anyang_yi.domain.usecase.keyword.WriteKeywordUseCase
import com.juhwan.anyang_yi.domain.usecase.univ.GetSearchResultListUseCase
import com.juhwan.anyang_yi.present.utils.Result
import com.juhwan.anyang_yi.present.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// TODO: searchUsecase로 만들기, keywordUsecase 만들기
@HiltViewModel
class KeywordViewModel @Inject constructor(
    private val deleteKeywordUseCase: DeleteKeywordUseCase,
    private val readKeywordListUseCase: ReadKeywordListUseCase,
    private val writeKeywordUseCase: WriteKeywordUseCase,
    private val getSearchResultListUseCase: GetSearchResultListUseCase
): ViewModel() {
    private val keywordList: LiveData<List<KeywordEntity>> = readKeywordListUseCase.invoke()

    private val _searchResultList = MutableLiveData<List<Univ>>()
    val searchResultList: LiveData<List<Univ>> get() = _searchResultList

    private val _problem = MutableLiveData<Result<Any>>()
    val problem: LiveData<Result<Any>> get() = _problem

    fun deleteKeyword(keyword: String) {
        viewModelScope.launch {
            try {
                deleteKeywordUseCase.invoke(keyword)
            } catch (e: Exception) {
                _problem.postValue(Result(Status.FAIL, null, null))
            }
        }
    }

    fun readKeywordList(): LiveData<List<KeywordEntity>> {
        return keywordList
    }

    fun writeKeyword(keyword: String) {
        viewModelScope.launch {
            val result = writeKeywordUseCase.invoke(keyword)
            if(result.status == Status.ERROR) {
                _problem.postValue(result)
            }
        }
    }

    fun getSearchResultList(keyword: String, offset: Int) {
        viewModelScope.launch {
            val result = getSearchResultListUseCase(keyword, offset)
            if(result.status == Status.SUCCESS) {
                result.data.let { _searchResultList.postValue(it) }
            } else {
                _problem.postValue(result)
            }
        }
    }
}