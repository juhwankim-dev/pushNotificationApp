package com.juhwan.anyang_yi.present.views.home.notice.univ

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.databinding.ItemUnivBinding
import com.juhwan.anyang_yi.domain.model.Univ
import com.juhwan.anyang_yi.present.views.home.WebViewActivity

class UnivAdapter : PagingDataAdapter<Univ, UnivAdapter.NoticeViewHolder>(diffCallback) {
    inner class NoticeViewHolder(private val binding: ItemUnivBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(univ: Univ) {
            binding.univ = univ
            binding.layoutNotice.setOnClickListener {
                var goUnivHomepage = Intent(it.context, WebViewActivity::class.java)
                goUnivHomepage.putExtra("url", univ.url)
                it.context.startActivity(goUnivHomepage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val binding = ItemUnivBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoticeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Univ>() {
            override fun areItemsTheSame(oldItem: Univ, newItem: Univ): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Univ, newItem: Univ): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }
}
