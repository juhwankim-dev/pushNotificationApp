package com.juhwan.anyang_yi.ui.notice.menu

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.databinding.ItemAriNoticeBinding
import com.juhwan.anyang_yi.network.AriNotice
import com.juhwan.anyang_yi.ui.notice.WebViewActivity
import java.text.SimpleDateFormat
import java.time.LocalDate

class AriNoticeAdapter : RecyclerView.Adapter<AriNoticeAdapter.AriNoticeViewHolder>() {
    private val items = ArrayList<AriNotice>()
    private val baseUrl = "https://ari.anyang.ac.kr/user/bbs/notice/"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : AriNoticeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAriNoticeBinding.inflate(layoutInflater, parent, false)
        return AriNoticeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: AriNoticeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setList(ariNotice: List<AriNotice>) {
        items.clear()
        items.addAll(ariNotice)
    }

    inner class AriNoticeViewHolder(private val binding: ItemAriNoticeBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(ariNotice: AriNotice){
            binding.tvNoticeTitle.text = ariNotice.title
            binding.tvNoticeDate.text = ariNotice.date
            binding.layoutNotice.setOnClickListener {
                var goPage = Intent(it.context, WebViewActivity::class.java)

                goPage.putExtra("url", baseUrl + ariNotice.link)
                it.context.startActivity(goPage)
            }
        }
    }
}