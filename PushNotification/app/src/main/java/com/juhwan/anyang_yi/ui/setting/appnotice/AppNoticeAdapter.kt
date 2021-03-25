package com.juhwan.anyang_yi.ui.setting.appnotice

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.databinding.ItemAppNoticeBinding
import com.juhwan.anyang_yi.ui.setting.appnotice.content.NoticeContentActivity

class AppNoticeAdapter(notices: ArrayList<AppNotice>) :
    RecyclerView.Adapter<AppNoticeAdapter.AppNoticeViewHolder>() {

    var items = notices

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : AppNoticeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAppNoticeBinding.inflate(layoutInflater, parent, false)
        return AppNoticeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: AppNoticeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class AppNoticeViewHolder(private val binding: ItemAppNoticeBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(appNotice: AppNotice) {
            binding.tvAppNoticeTitle.text = appNotice.title
            binding.tvAppNoticeDate.text = appNotice.date

            if(appNotice.isNew == "true") binding.ivNew.visibility = View.VISIBLE

            binding.layoutAppNotice.setOnClickListener {
                var intent = Intent(it.context, NoticeContentActivity::class.java)
                intent.putExtra("title", appNotice.title)
                intent.putExtra("date", appNotice.date)
                intent.putExtra("content", appNotice.content)
                it.context.startActivity(intent)
            }
        }
    }
}