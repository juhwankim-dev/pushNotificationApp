package com.juhwan.anyang_yi.ui.notice.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.juhwan.anyang_yi.network.Result
import com.juhwan.anyang_yi.repository.MainNoticeRepository

class MainNoticeViewModel: ViewModel() {
    private val repository = MainNoticeRepository()

    private val entireNotice: LiveData<Result>
    get() = repository._entireNotice

    private val univNotice: LiveData<Result>
        get() = repository._univNotice

    fun getMainNotices(page: Int, menu: String) {
        repository.getMainNotices(page, menu)
    }

    fun getAll(menu: String): LiveData<Result> {
        when(menu){
            "전체" -> return entireNotice
        }
        return univNotice
    }
}