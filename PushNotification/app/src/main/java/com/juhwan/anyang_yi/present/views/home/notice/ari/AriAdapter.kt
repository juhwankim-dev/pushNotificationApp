package com.juhwan.anyang_yi.present.views.home.notice.ari

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.data.model.AriNoticeList
import com.juhwan.anyang_yi.databinding.ItemLoadingBinding
import com.juhwan.anyang_yi.databinding.ItemNoticeBinding
import com.juhwan.anyang_yi.present.views.home.WebViewActivity

class AriAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1
    private val baseUrl = "https://ari.anyang.ac.kr/user/bbs/notice/"

    private var items = ArrayList<AriNoticeList>()

    inner class NoticeViewHolder(private val binding: ItemNoticeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(notice: AriNoticeList) {
            binding.tvNoticeTitle.text = notice.title
            binding.tvNoticeDate.text = notice.date

            var hms2 = notice.date + " 00:00:00"
            var writeDate = InitialRepository.sf.parse(hms2)
            var calculateDate = (InitialRepository.todayDate.time - writeDate.time) / (60 * 60 * 24 * 1000)

            if(calculateDate.toInt() == 0){
                binding.ivNew.visibility = View.VISIBLE
            } else {
                binding.ivNew.visibility = View.GONE
            }

            binding.layoutNotice.setOnClickListener {
                var goUnivHomepage = Intent(it.context, WebViewActivity::class.java)

                goUnivHomepage.putExtra("url", baseUrl + notice.link)
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
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemNoticeBinding.inflate(layoutInflater, parent, false)
                NoticeViewHolder(binding)
            }
            else -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemLoadingBinding.inflate(layoutInflater, parent, false)
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

    fun setList(notice: ArrayList<AriNoticeList>) {
        items.addAll(notice)
    }

    fun deleteLoading(){
        items.removeAt(items.lastIndex)
    }
}
