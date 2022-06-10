package com.juhwan.anyang_yi.present.views.home.notice.univ

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.databinding.ItemLoadingBinding
import com.juhwan.anyang_yi.databinding.ItemUnivBinding
import com.juhwan.anyang_yi.domain.model.Univ
import com.juhwan.anyang_yi.present.views.home.WebViewActivity

class UnivAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    private var items = ArrayList<Univ>()

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

    inner class LoadingViewHolder(private val binding: ItemLoadingBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    // 뷰의 타입을 정해주는 곳이다.
    override fun getItemViewType(position: Int): Int {
//        return when (items[position].SUBJECT) {
//            " " -> VIEW_TYPE_LOADING
//            else -> VIEW_TYPE_ITEM
//        }
        return VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_ITEM -> {
                val binding = ItemUnivBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

    fun setList(notice: List<Univ>) {
        items.addAll(notice)
    }

    fun resetList() {
        items.clear()
        notifyDataSetChanged()
    }

    fun deleteLoading(){
        items.removeAt(items.lastIndex)
    }
}
