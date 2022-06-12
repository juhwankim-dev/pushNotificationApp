package com.juhwan.anyang_yi.present.views.home.notice.ari

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.databinding.ItemAriBinding
import com.juhwan.anyang_yi.domain.model.Ari
import com.juhwan.anyang_yi.present.views.home.WebViewActivity

class AriAdapter : PagingDataAdapter<Ari, AriAdapter.NoticeViewHolder>(diffCallback) {
    inner class NoticeViewHolder(private val binding: ItemAriBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(ari: Ari) {
            binding.ari = ari
            binding.layoutNotice.setOnClickListener {
                var goUnivHomepage = Intent(it.context, WebViewActivity::class.java)
                goUnivHomepage.putExtra("url", ari.link)
                it.context.startActivity(goUnivHomepage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val binding = ItemAriBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoticeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Ari>() {
            override fun areItemsTheSame(oldItem: Ari, newItem: Ari): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Ari, newItem: Ari): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }
}
