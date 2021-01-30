package com.example.pushnotification.fragments.setting

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.pushnotification.R
import com.example.pushnotification.fragments.home.WebViewActivity
import kotlinx.android.synthetic.main.app_notice_list_item.view.*

class AppNoticeAdapter(notices: ArrayList<AppNotice>) :
    RecyclerView.Adapter<AppNoticeAdapter.AppNoticeViewHolder>() {

    var items = notices

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppNoticeViewHolder {
        return AppNoticeViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: AppNoticeViewHolder, position: Int) {
        if(items[position].isNew){
            holder.imageViewNew.visibility = View.VISIBLE
        }

        holder.txtAppNoticeTitle.text = items[position].title
        holder.txtAppNoticeDate.text = items[position].date
        holder.layoutAppNotice.setOnClickListener {
            var intent = Intent(it.context, NoticeContentActivity::class.java)
            intent.putExtra("title", items[position].title)
            intent.putExtra("date", items[position].date)
            intent.putExtra("content", items[position].content)
            intent.putExtra("isNew", items[position].isNew)
            it.context.startActivity(intent)
        }
    }

    inner class AppNoticeViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.app_notice_list_item, parent, false)) {

        var txtAppNoticeTitle = itemView.txt_app_notice_title!!
        var txtAppNoticeDate = itemView.txt_app_notice_date!!
        var layoutAppNotice = itemView.layout_app_notice!!
        var imageViewNew = itemView.imageView_new
    }
}