package com.juhwan.anyang_yi.present.views.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juhwan.anyang_yi.domain.model.Ari
import com.juhwan.anyang_yi.domain.model.Contact
import com.juhwan.anyang_yi.domain.usecase.ari.GetAriListUseCase
import com.juhwan.anyang_yi.domain.usecase.contact.GetContactUseCase
import com.juhwan.anyang_yi.domain.usecase.nonsubject.GetNonsubjectListUseCase
import com.juhwan.anyang_yi.domain.usecase.univ.GetUnivListUseCase
import com.juhwan.anyang_yi.present.utils.Result
import com.juhwan.anyang_yi.present.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val getContactUseCase: GetContactUseCase
): ViewModel() {
    private val _contact = MutableLiveData<Contact>()
    val contact: LiveData<Contact> get() = _contact

    private val _problem = MutableLiveData<Result<Any>>()
    val problem: LiveData<Result<Any>> get() = _problem

    fun getContact() {
        viewModelScope.launch {
            val result = getContactUseCase()
            if(result.status == Status.SUCCESS) {
                result.data.let { _contact.postValue(it) }
            } else {
                _problem.postValue(result)
            }
        }
    }
}