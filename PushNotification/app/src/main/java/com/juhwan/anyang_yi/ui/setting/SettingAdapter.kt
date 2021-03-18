package com.juhwan.anyang_yi.ui.setting

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.databinding.ItemSettingBinding
import com.juhwan.anyang_yi.ui.setting.appnotice.AppNoticeActivity
import com.juhwan.anyang_yi.ui.setting.license.LicenseActivity
import com.juhwan.anyang_yi.ui.setting.profile.ProfileActivity

class SettingAdapter() : RecyclerView.Adapter<SettingAdapter.SettingViewHolder>() {
    var items = listOf<String>(("공지사항"), ("문의하기"), ("개발자 정보"), ("오픈소스 라이센스"))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : SettingViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSettingBinding.inflate(layoutInflater, parent, false)
        return SettingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SettingViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class SettingViewHolder(private val binding: ItemSettingBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(str: String) {
            when(str){
                "공지사항" -> {
                    binding.tvSetting.text = "공지사항"
                    binding.layoutSetting.setOnClickListener {
                        it.context.startActivity((Intent(it.context, AppNoticeActivity::class.java)))
                    }
                }
                "문의하기" -> {
                    binding.tvSetting.text = "문의하기"
                    binding.layoutSetting.setOnClickListener {

                    }
                }
                "개발자 정보" -> {
                    binding.tvSetting.text = "개발자 정보"
                    binding.layoutSetting.setOnClickListener {
                        it.context.startActivity((Intent(it.context, ProfileActivity::class.java)))
                    }
                }
                "오픈소스 라이센스" -> {
                    binding.tvSetting.text = "오픈소스 라이센스"
                    binding.layoutSetting.setOnClickListener {
                        it.context.startActivity((Intent(it.context, LicenseActivity::class.java)))
                    }
                }
            }
        }
    }
}