package com.juhwan.anyang_yi.present.views.home.notice

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.databinding.ItemNoticeBinding
import com.juhwan.anyang_yi.domain.model.Univ
import com.juhwan.anyang_yi.present.views.home.WebViewActivity

class RecentUnivAdapter : RecyclerView.Adapter<RecentUnivAdapter.MainNoticeViewHolder>() {
    private val items = ArrayList<Univ>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MainNoticeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNoticeBinding.inflate(layoutInflater, parent, false)
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

    inner class MainNoticeViewHolder(private val binding: ItemNoticeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(notice: Univ){
            binding.tvNoticeTitle.text = notice.title
            binding.tvNoticeDate.text = notice.date

            if(notice.isNew){
                binding.ivNew.visibility = View.VISIBLE
            } else {
                binding.ivNew.visibility = View.GONE
            }

            binding.layoutNotice.setOnClickListener {
                var goPage = Intent(it.context, WebViewActivity::class.java)

                goPage.putExtra("url", notice.url)
                it.context.startActivity(goPage)
            }
        }
    }
}