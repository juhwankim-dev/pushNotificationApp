package com.juhwan.anyang_yi.present.views.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.data.model.AriNoticeList
import com.juhwan.anyang_yi.databinding.ItemNoticeBinding

class RecentAriAdapter : RecyclerView.Adapter<RecentAriAdapter.AriNoticeViewHolder>() {
    private val items = ArrayList<AriNoticeList>()
    private val baseUrl = "https://ari.anyang.ac.kr/user/bbs/notice/"

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

    fun setList(ariNotice: MutableList<AriNoticeList>) {
        items.addAll(ariNotice)
    }

    inner class AriNoticeViewHolder(private val binding: ItemNoticeBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(ariNotice: AriNoticeList){
            binding.tvNoticeTitle.text = ariNotice.title
            binding.tvNoticeDate.text = ariNotice.date

            var hms2 = ariNotice.date + " 00:00:00"
            var writeDate = InitialRepository.sf.parse(hms2)
            var calculateDate = (InitialRepository.todayDate.time - writeDate.time) / (60 * 60 * 24 * 1000)

            if(calculateDate.toInt() == 0){
                binding.ivNew.visibility = View.VISIBLE
            } else {
                binding.ivNew.visibility = View.GONE
            }

            binding.layoutNotice.setOnClickListener {
                var goPage = Intent(it.context, WebViewActivity::class.java)

                goPage.putExtra("url", baseUrl + ariNotice.link)
                it.context.startActivity(goPage)
            }
        }
    }
}