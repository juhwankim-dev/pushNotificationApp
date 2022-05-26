package com.juhwan.anyang_yi.present.views.notice.all

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.data.model.ResultList
import com.juhwan.anyang_yi.databinding.ItemLoadingBinding
import com.juhwan.anyang_yi.databinding.ItemNoticeBinding
import com.juhwan.anyang_yi.data.repository.InitialRepository
import com.juhwan.anyang_yi.data.repository.InitialRepository.sf
import com.juhwan.anyang_yi.present.views.notice.WebViewActivity

class AllMainNoticeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1
    private var baseUrl = "http://www.anyang.ac.kr/bbs/boardView.do?bsIdx=61&menuId=23&bcIdx=20&bIdx="

    private var items = ArrayList<ResultList>()

    inner class NoticeViewHolder(private val binding: ItemNoticeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(notice: ResultList) {
            binding.tvNoticeTitle.text = notice.SUBJECT
            binding.tvNoticeDate.text = notice.WRITE_DATE2 + "   |   " + notice.WRITER

            var hms2 = notice.WRITE_DATE2 + " 00:00:00"
            var writeDate = sf.parse(hms2)
            var calculateDate = (InitialRepository.todayDate.time - writeDate.time) / (60 * 60 * 24 * 1000)

            if(calculateDate.toInt() == 0){
                binding.ivNew.visibility = View.VISIBLE
            } else {
                binding.ivNew.visibility = View.GONE
            }

            binding.layoutNotice.setOnClickListener {
                var goUnivHomepage = Intent(it.context, WebViewActivity::class.java)

                goUnivHomepage.putExtra("url", baseUrl + notice.B_IDX)
                it.context.startActivity(goUnivHomepage)
            }
        }
    }

    inner class LoadingViewHolder(private val binding: ItemLoadingBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    // 뷰의 타입을 정해주는 곳이다.
    override fun getItemViewType(position: Int): Int {
        return when (items[position].SUBJECT) {
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

    fun setList(notice: ArrayList<ResultList>) {
        items.addAll(notice)
    }

    fun resetList(bcIdx: String) {
        items.clear()
        baseUrl = "http://www.anyang.ac.kr/bbs/boardView.do?bsIdx=61&menuId=23&bcIdx=$bcIdx&bIdx="
    }

    fun deleteLoading(){
        items.removeAt(items.lastIndex)
    }
}
