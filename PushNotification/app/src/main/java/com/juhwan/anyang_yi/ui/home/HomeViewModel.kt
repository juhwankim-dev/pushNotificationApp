package com.juhwan.anyang_yi.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.juhwan.anyang_yi.network.Result
import com.juhwan.anyang_yi.repository.NoticeRepository

class HomeViewModel: ViewModel() {
    private val repository = NoticeRepository()

    private val noticeInfo: LiveData<Result>
    get() = repository._noticeInfo

    fun requestPost(page: Int) {
        repository.requestPost(page)
    }

    fun getAll(): LiveData<Result> {
        return noticeInfo
    }
}