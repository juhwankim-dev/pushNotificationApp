package com.juhwan.anyang_yi.present.views.home.notice.univ

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.juhwan.anyang_yi.domain.model.Univ
import com.juhwan.anyang_yi.domain.usecase.univ.GetUnivListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class UnivViewModel @Inject constructor(
    private val getUnivListUseCase: GetUnivListUseCase
): ViewModel() {
    fun changeCategory(categoryId: String): Flow<PagingData<Univ>> {
        return getUnivListUseCase(categoryId).cachedIn(viewModelScope)
    }
}