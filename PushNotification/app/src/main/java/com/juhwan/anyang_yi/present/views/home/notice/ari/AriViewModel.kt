package com.juhwan.anyang_yi.present.views.home.notice.ari

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.juhwan.anyang_yi.domain.model.Ari
import com.juhwan.anyang_yi.domain.usecase.ari.GetAriListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AriViewModel @Inject constructor(
    private val getAriListUseCase: GetAriListUseCase
): ViewModel() {
    fun getePagingData(): Flow<PagingData<Ari>> {
        return getAriListUseCase().cachedIn(viewModelScope)
    }
}