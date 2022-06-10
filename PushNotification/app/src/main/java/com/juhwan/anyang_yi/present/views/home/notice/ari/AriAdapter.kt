package com.juhwan.anyang_yi.present.views.home.notice.ari

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.databinding.ItemAriBinding
import com.juhwan.anyang_yi.databinding.ItemLoadingBinding
import com.juhwan.anyang_yi.domain.model.Ari
import com.juhwan.anyang_yi.present.views.home.WebViewActivity

class AriAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1
    private var items = ArrayList<Ari>()

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

    inner class LoadingViewHolder(private val binding: ItemLoadingBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    // 뷰의 타입을 정해주는 곳이다.
    override fun getItemViewType(position: Int): Int {
        return when (items[position].title) {
            " " -> VIEW_TYPE_LOADING
            else -> VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM -> {
                val binding = ItemAriBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                NoticeViewHolder(binding)
            }
            else -> {
                val binding = ItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                LoadingViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is NoticeViewHolder){
            holder.bind(items[position])
        }else{

        }
    }

    fun setList(notice: List<Ari>) {
        items.addAll(notice)
    }

    fun deleteLoading(){
        items.removeAt(items.lastIndex)
    }
}
