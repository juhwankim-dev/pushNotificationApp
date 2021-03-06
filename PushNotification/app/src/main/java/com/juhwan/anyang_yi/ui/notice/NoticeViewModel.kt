package com.juhwan.anyang_yi.ui.notice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juhwan.anyang_yi.data.Result
import com.juhwan.anyang_yi.repository.AriNoticeRepository
import com.juhwan.anyang_yi.repository.InitialRepository
import com.juhwan.anyang_yi.repository.MainNoticeRepository

class NoticeViewModel : ViewModel() {
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