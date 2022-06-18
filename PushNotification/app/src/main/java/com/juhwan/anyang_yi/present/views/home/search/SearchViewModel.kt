package com.juhwan.anyang_yi.present.views.home.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.juhwan.anyang_yi.domain.model.Ari
import com.juhwan.anyang_yi.domain.model.Univ
import com.juhwan.anyang_yi.domain.usecase.ari.GetSearchAriListUseCase
import com.juhwan.anyang_yi.domain.usecase.univ.GetSearchUnivListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchUnivListUseCase: GetSearchUnivListUseCase,
    private val getSearchAriListUseCase: GetSearchAriListUseCase
): ViewModel() {
    fun getSearchUnivPagingData(keyword: String): Flow<PagingData<Univ>> {
        return getSearchUnivListUseCase(keyword).cachedIn(viewModelScope)
    }
    fun getSearchAriPagingData(keyword: String): Flow<PagingData<Ari>> {
        return getSearchAriListUseCase(keyword).cachedIn(viewModelScope)
    }
}