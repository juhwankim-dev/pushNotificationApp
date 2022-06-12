package com.juhwan.anyang_yi.present.views.setting.appnotice

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.databinding.ItemAppNoticeBinding
import com.juhwan.anyang_yi.domain.model.AppNotice
import com.juhwan.anyang_yi.present.views.setting.appnotice.detail.AppNoticeDetailActivity

class AppNoticeAdapter : RecyclerView.Adapter<AppNoticeAdapter.AppNoticeViewHolder>() {
    var items = ArrayList<AppNotice>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : AppNoticeViewHolder {
        val binding = ItemAppNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AppNoticeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: AppNoticeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setList(list: List<AppNotice>) {
        items.addAll(list.reversed())
    }

    inner class AppNoticeViewHolder(private val binding: ItemAppNoticeBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(appNotice: AppNotice) {
            binding.appNotice = appNotice
            binding.layoutAppNotice.setOnClickListener {
                var intent = Intent(it.context, AppNoticeDetailActivity::class.java)
                intent.putExtra("title", appNotice.title)
                intent.putExtra("date", appNotice.date)
                intent.putExtra("content", appNotice.content)
                it.context.startActivity(intent)
            }
        }
    }
}