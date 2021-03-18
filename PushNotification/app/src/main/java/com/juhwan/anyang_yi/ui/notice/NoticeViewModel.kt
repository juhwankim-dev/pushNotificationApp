package com.juhwan.anyang_yi.ui.notice

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.juhwan.anyang_yi.network.Result
import com.juhwan.anyang_yi.network.ResultList
import com.juhwan.anyang_yi.repository.AriApplyNoticeRepository
import com.juhwan.anyang_yi.repository.AriNoticeRepository
import com.juhwan.anyang_yi.repository.MainNoticeRepository
import com.juhwan.anyang_yi.ui.notice.menu.AriNoticeFragment

class NoticeViewModel : ViewModel() {

    private val mainNotice: LiveData<Result>
        get() = MainNoticeRepository._mainNotice

    init {
        getMainNotices(1)
    }

    fun getMainNotices(page: Int) {
        MainNoticeRepository.getMainNotices(page)
    }

    fun getAll(): LiveData<Result> {
        return mainNotice
    }
}