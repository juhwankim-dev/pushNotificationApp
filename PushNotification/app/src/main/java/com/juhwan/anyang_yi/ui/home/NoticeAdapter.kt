package com.juhwan.anyang_yi.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.databinding.ItemNoticeBinding
import com.juhwan.anyang_yi.network.Result


class NoticeAdapter : RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>(){

    private val items = ArrayList<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : NoticeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNoticeBinding.inflate(layoutInflater)
        return NoticeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setList(noticeInfo: Result) {
        items.clear()
        items.addAll(listOf(noticeInfo))
    }

    inner class NoticeViewHolder(private val binding: ItemNoticeBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(noticeInfo: Result){
            binding.tvNoticeTitle.text = noticeInfo.resultList[0].SUBJECT
            binding.tvNoticeDateWriter.text = noticeInfo.resultList[0].WRITER
            //TODO 링크랑 이거 htmlcrawler보고 수정하기
        }
    }

/*    var notices = notices
    val context = context
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    // 아이템뷰에 게시물이 들어가는 경우
    inner class MyNoticeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title = itemView.txtNoticeTitle!! // 텍스트뷰의 값을 가져와 저장
        val info = itemView.txtNoticeInfo!! // 텍스트뷰의 값을 가져와 저장
    }

    // 아이템뷰에 프로그레스바가 들어가는 경우
    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val progressBar = itemView.progressBar
    }

    // 뷰의 타입을 정해주는 곳이다.
    override fun getItemViewType(position: Int): Int {
        return when (notices[position].title){
            "loading" -> VIEW_TYPE_LOADING
            else -> VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View

        return when(viewType){
            VIEW_TYPE_ITEM -> {
                view = LayoutInflater.from(context).inflate(R.layout.notice_list_item, parent, false)
                MyNoticeViewHolder(view)
            }
            else -> {
                view = LayoutInflater.from(context).inflate(R.layout.item_loading, parent, false)
                LoadingViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is MyNoticeViewHolder) {
            holder.title.text = notices[position].title
            holder.info.text = notices[position].info

            holder.itemView.setOnClickListener { v ->
                var goUnivHomepage = Intent(v.context, WebViewActivity::class.java)
                var url = "http://www.anyang.ac.kr/bbs/boardView.do?bsIdx=61&bIdx=" +
                        notices[position].url + "&page=1&menuId=23&bcIdx=0&searchCondition=SUBJECT&searchKeyword="

                goUnivHomepage.putExtra("url", url)
                v.context.startActivity(goUnivHomepage);
            }
        } else if(holder is LoadingViewHolder){
            // 프로그레스바 있는곳
        }
    }

    override fun getItemCount(): Int {
        return notices.size
    }*/
}