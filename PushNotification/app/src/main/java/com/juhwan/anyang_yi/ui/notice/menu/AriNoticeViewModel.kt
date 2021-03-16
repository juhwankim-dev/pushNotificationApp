package com.juhwan.anyang_yi.ui.notice.menu

import android.util.Log
import androidx.lifecycle.ViewModel
import com.juhwan.anyang_yi.repository.AriNoticeRepository

class AriNoticeViewModel : ViewModel(){
    private val repository = AriNoticeRepository()

    fun getAriNotices(
        page: Int,
        listener: AriNoticeFragment
    ){
        repository.getAriNotices(page, listener)
    }
}