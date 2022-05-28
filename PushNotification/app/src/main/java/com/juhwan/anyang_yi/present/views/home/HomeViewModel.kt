package com.juhwan.anyang_yi.present.views.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.juhwan.anyang_yi.data.model.Result
import com.juhwan.anyang_yi.data.repository.AriNoticeRepository
import com.juhwan.anyang_yi.data.repository.MainNoticeRepository

class HomeViewModel : ViewModel() {
    private var mainNoticeRepository = MainNoticeRepository()
    private var ariNoticeRepository = AriNoticeRepository()

    private val initMainNotice: LiveData<Result>
        get() = mainNoticeRepository._initMainNotice

    init {
        loadInitialData()
    }

    fun loadInitialData() {
        mainNoticeRepository.loadInitialMainNotice()
        ariNoticeRepository.loadInitialAriNotice()
    }

    fun getAll(): LiveData<Result> {
        return initMainNotice
    }
}