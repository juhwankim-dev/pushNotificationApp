package com.juhwan.anyang_yi.ui.notice.all

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.juhwan.anyang_yi.data.Result
import com.juhwan.anyang_yi.repository.MainNoticeRepository

class MainNoticeViewModel : ViewModel() {

    private val mainNoticeRepository = MainNoticeRepository()

    private val mainNotice: LiveData<Result>
        get() = mainNoticeRepository._mainNotice

    fun loadMainNotice(page: Int) {
        mainNoticeRepository.loadMainNotice(page)
    }

    fun getAll(): LiveData<Result> {
        return mainNotice
    }
}