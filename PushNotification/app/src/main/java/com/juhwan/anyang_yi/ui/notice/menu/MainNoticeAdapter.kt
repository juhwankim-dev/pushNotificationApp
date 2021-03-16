package com.juhwan.anyang_yi.ui.notice.menu

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.databinding.ItemLoadingBinding
import com.juhwan.anyang_yi.databinding.ItemNoticeBinding
import com.juhwan.anyang_yi.network.ResultList
import com.juhwan.anyang_yi.ui.notice.WebViewActivity
import java.text.SimpleDateFormat
import java.time.LocalDate
import kotlin.collections.ArrayList


class MainNoticeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1
    private val baseUrl = "http://www.anyang.ac.kr/bbs/boardView.do?bsIdx=61&menuId=23&bcIdx=20&bIdx="
    var sf = SimpleDateFormat("yyyy-MM-dd")
    private var today = LocalDate.now()
    private var date1 = "$today 00:00:00"
    private var todayDate = sf.parse(date1)

    private var items = ArrayList<ResultList>()

    inner class NoticeViewHolder(private val binding: ItemNoticeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(notice: ResultList) {
            binding.tvNoticeTitle.text = notice.SUBJECT
            binding.tvNoticeDateWriter.text = notice.WRITE_DATE2 + "   |   " + notice.WRITER

            var hms2 = notice.WRITE_DATE2 + " 00:00:00"
            var writeDate = sf.parse(hms2)
            var calculateDate = (todayDate.time - writeDate.time) / (60 * 60 * 24 * 1000)

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
            null -> VIEW_TYPE_LOADING
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

    fun setList(notice: Array<ResultList>) {
        items.addAll(notice)
        items.add(ResultList()) // progress bar를 넣기 위해 빈 element 하나 삽입
    }

    fun deleteLoding(){
        items.removeAt(items.lastIndex)
    }
}