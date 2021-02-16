package com.juhwan.anyang_yi.fragments.calendar

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.fragments.home.MyNoticeAdapter
import com.juhwan.anyang_yi.fragments.home.WebViewActivity
import kotlinx.android.synthetic.main.schedule_list_item.view.*

class ScheduleAdapter(items: List<Schedule>) : RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder>() {
    var items = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ScheduleViewHolder((parent)) // 뷰홀더 생성

    override fun getItemCount(): Int { // 아이템의 총 갯수 반환
        return items.size
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) { // 생선된 뷰홀더에 데이터 삽입
        holder.title.text = items[position].title
        holder.date.text = items[position].date
    }

    inner class ScheduleViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.schedule_list_item, parent, false)){

        val title = itemView.txtview_schedule_title
        val date = itemView.txtview_schedule_date
    }
}