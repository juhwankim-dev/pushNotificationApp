package com.juhwan.anyang_yi.present.views.notice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.data.model.ResultList
import com.juhwan.anyang_yi.databinding.ItemNoticeBinding

class MainNoticeAdapter : RecyclerView.Adapter<MainNoticeAdapter.MainNoticeViewHolder>() {
    private val items = ArrayList<ResultList>()
    private val baseUrl = "http://www.anyang.ac.kr/bbs/boardView.do?bsIdx=61&menuId=23&bcIdx=20&bIdx="

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

    fun setList(notice: MutableList<ResultList>) {
        items.addAll(notice)
    }

    inner class MainNoticeViewHolder(private val binding: ItemNoticeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(notice: ResultList){
            //binding.resultList = notice

            /*
            binding.tvNoticeTitle.text = notice.SUBJECT
            binding.tvNoticeDate.text = notice.WRITE_DATE2 + "   |   " + notice.WRITER

            var hms2 = notice.WRITE_DATE2 + " 00:00:00"
            var writeDate = InitialRepository.sf.parse(hms2)
            var calculateDate = (InitialRepository.todayDate.time - writeDate.time) / (60 * 60 * 24 * 1000)

            if(calculateDate.toInt() == 0){
                binding.ivNew.visibility = View.VISIBLE
            } else {
                binding.ivNew.visibility = View.GONE
            }

            binding.layoutNotice.setOnClickListener {
                var goPage = Intent(it.context, WebViewActivity::class.java)

                goPage.putExtra("url", baseUrl + notice.B_IDX)
                it.context.startActivity(goPage)
            }

             */
        }
    }
}