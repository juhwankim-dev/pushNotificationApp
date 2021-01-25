package com.example.pushnotification.fragments.home

import io.reactivex.subjects.PublishSubject

object RxEventBusHelper {

    val mSubject = PublishSubject.create<ArrayList<NoticeList>>()

    fun sendEvent(notices: ArrayList<NoticeList>) {
        mSubject.onNext(notices)
    }
}