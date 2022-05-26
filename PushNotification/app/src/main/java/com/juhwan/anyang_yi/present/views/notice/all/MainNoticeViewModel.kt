package com.juhwan.anyang_yi.present.views.notice.all

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.juhwan.anyang_yi.data.model.Result
import com.juhwan.anyang_yi.data.repository.MainNoticeRepository

class MainNoticeViewModel : ViewModel() {

    private val mainNoticeRepository = MainNoticeRepository()

    private val mainNotice: LiveData<Result>
        get() = mainNoticeRepository._mainNotice

    fun loadMainNotice(page: Int, bcIdx: String) {
        mainNoticeRepository.loadMainNotice(page, bcIdx)
    }

    fun getAll(): LiveData<Result> {
        return mainNotice
    }
}