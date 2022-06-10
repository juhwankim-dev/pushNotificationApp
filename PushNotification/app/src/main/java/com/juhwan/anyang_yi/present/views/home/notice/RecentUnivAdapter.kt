package com.juhwan.anyang_yi.present.views.home.notice

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.databinding.ItemUnivBinding
import com.juhwan.anyang_yi.domain.model.Univ
import com.juhwan.anyang_yi.present.views.home.WebViewActivity

class RecentUnivAdapter : RecyclerView.Adapter<RecentUnivAdapter.MainNoticeViewHolder>() {
    private val items = ArrayList<Univ>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MainNoticeViewHolder {
        val binding = ItemUnivBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainNoticeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MainNoticeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setList(notice: List<Univ>) {
        items.addAll(notice)
        notifyDataSetChanged()
    }

    inner class MainNoticeViewHolder(private val binding: ItemUnivBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(univ: Univ){
            binding.univ = univ
            binding.layoutNotice.setOnClickListener {
                var goPage = Intent(it.context, WebViewActivity::class.java)
                goPage.putExtra("url", univ.url)
                it.context.startActivity(goPage)
            }
        }
    }
}