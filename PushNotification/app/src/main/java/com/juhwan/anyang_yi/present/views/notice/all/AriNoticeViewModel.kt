package com.juhwan.anyang_yi.present.views.notice.all

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.juhwan.anyang_yi.data.model.AriNotice
import com.juhwan.anyang_yi.data.repository.AriNoticeRepository

class AriNoticeViewModel : ViewModel() {

    private val ariNoticeRepository = AriNoticeRepository()

    private val ariNotice: LiveData<AriNotice>
        get() = ariNoticeRepository._ariNotice

    fun loadAriNotice(page: Int) {
        ariNoticeRepository.loadAriNotice(page)
    }

    fun getAll(): LiveData<AriNotice> {
        return ariNotice
    }
}