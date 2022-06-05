package com.juhwan.anyang_yi.present.views.home.notice

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.databinding.ItemNoticeBinding
import com.juhwan.anyang_yi.domain.model.Ari
import com.juhwan.anyang_yi.present.views.home.WebViewActivity

class RecentAriAdapter : RecyclerView.Adapter<RecentAriAdapter.AriNoticeViewHolder>() {
    private val items = ArrayList<Ari>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : AriNoticeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNoticeBinding.inflate(layoutInflater, parent, false)
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

    inner class AriNoticeViewHolder(private val binding: ItemNoticeBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(ariNotice: Ari){
            binding.tvNoticeTitle.text = ariNotice.title
            binding.tvNoticeDate.text = ariNotice.date

            if(ariNotice.date == "0"){
                binding.ivNew.visibility = View.VISIBLE
            } else {
                binding.ivNew.visibility = View.GONE
            }

            binding.layoutNotice.setOnClickListener {
                var goPage = Intent(it.context, WebViewActivity::class.java)

                goPage.putExtra("url", ariNotice.link)
                it.context.startActivity(goPage)
            }
        }
    }
}