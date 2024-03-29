package com.juhwan.anyang_yi.present.views.setting

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.databinding.ItemSettingBinding
import com.juhwan.anyang_yi.present.views.setting.appnotice.AppNoticeActivity
import com.juhwan.anyang_yi.present.views.setting.license.LicenseActivity
import com.juhwan.anyang_yi.present.views.setting.profile.ProfileActivity

class SettingAdapter : RecyclerView.Adapter<SettingAdapter.SettingViewHolder>() {
    private lateinit var inquiryClickListener: InquiryClickListener
    var items = listOf("앱 공지사항", "건의하기", "개발자 정보", "오픈소스 라이센스")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingViewHolder {
        val binding = ItemSettingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SettingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SettingViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    inner class SettingViewHolder(private val binding: ItemSettingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(name: String, position: Int) {
            binding.name = name

            binding.clMenu.setOnClickListener {
                when (position) {
                    0 -> it.context.startActivity(Intent(itemView.context, AppNoticeActivity::class.java))
                    1 -> inquiryClickListener.onClick()
                    2 -> it.context.startActivity(Intent(itemView.context, ProfileActivity::class.java))
                    3 -> it.context.startActivity(Intent(itemView.context, LicenseActivity::class.java))
                }
            }
        }
    }

    interface InquiryClickListener {
        fun onClick()
    }

    fun setInquiryClickListener(inquiryClickListener: InquiryClickListener) {
        this.inquiryClickListener = inquiryClickListener
    }
}