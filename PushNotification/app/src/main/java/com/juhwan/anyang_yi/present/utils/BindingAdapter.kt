package com.juhwan.anyang_yi.present.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.present.views.home.RecentUnivAdapter

// 메인 공지사항 바인딩 어댑터

object BindingAdapter {
    private val RECENT_UNIV_ADATPER: RecentUnivAdapter = RecentUnivAdapter()

    @JvmStatic
    @BindingAdapter("items")
    fun setItems(recyclerView: RecyclerView, items: Result?) {
        // recyclerView 어댑터를 생성하지 않았다면 생성
        if(recyclerView.adapter == null) {
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
            recyclerView.adapter = RECENT_UNIV_ADATPER
            recyclerView.itemAnimator = null
        }

        if(items != null) {
            RECENT_UNIV_ADATPER.setList(items.resultList)
            //mainNoticeAdatper.notifyItemRangeInserted()
        }
    }
}