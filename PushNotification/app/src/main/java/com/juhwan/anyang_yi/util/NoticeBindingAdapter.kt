package com.juhwan.anyang_yi.util

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.data.Result
import com.juhwan.anyang_yi.ui.notice.MainNoticeAdapter

// 메인 공지사항 바인딩 어댑터

object NoticeBindingAdapter {
    private val mainNoticeAdatper: MainNoticeAdapter = MainNoticeAdapter()

    @JvmStatic
    @BindingAdapter("items")
    fun setItems(recyclerView: RecyclerView, items: Result?) {
        // recyclerView 어댑터를 생성하지 않았다면 생성
        if(recyclerView.adapter == null) {
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
            recyclerView.adapter = mainNoticeAdatper
            recyclerView.itemAnimator = null
        }

        if(items != null) {
            mainNoticeAdatper.setList(items.resultList)
            mainNoticeAdatper.notifyItemRangeInserted()
        }
    }
}