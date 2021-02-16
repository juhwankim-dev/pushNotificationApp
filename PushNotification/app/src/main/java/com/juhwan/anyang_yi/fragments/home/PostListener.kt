package com.juhwan.anyang_yi.fragments.home

interface PostListener {
    fun loadPage(notices: ArrayList<NoticeList>, page: Int)
}