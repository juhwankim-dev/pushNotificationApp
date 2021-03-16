package com.juhwan.anyang_yi.ui.notice.menu

import com.juhwan.anyang_yi.network.AriNotice

interface AriUpdateListener {
    fun update(ariNotice: ArrayList<AriNotice>)
}