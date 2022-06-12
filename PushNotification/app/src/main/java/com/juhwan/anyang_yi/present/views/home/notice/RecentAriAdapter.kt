package com.juhwan.anyang_yi.present.views.home.notice

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.databinding.ItemAriBinding
import com.juhwan.anyang_yi.domain.model.Ari
import com.juhwan.anyang_yi.present.views.home.WebViewActivity

class RecentAriAdapter : RecyclerView.Adapter<RecentAriAdapter.AriNoticeViewHolder>() {
    private val items = ArrayList<Ari>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : AriNoticeViewHolder {
        val binding = ItemAriBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AriNoticeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: AriNoticeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setList(ariNotice: List<Ari>) {
        items.addAll(ariNotice)
        notifyDataSetChanged()
    }

    inner class AriNoticeViewHolder(private val binding: ItemAriBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(ari: Ari){
            binding.ari = ari

            binding.layoutNotice.setOnClickListener {
                var goPage = Intent(it.context, WebViewActivity::class.java)
                goPage.putExtra("url", ari.link)
                it.context.startActivity(goPage)
            }
        }
    }
}