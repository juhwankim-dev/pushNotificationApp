package com.example.pushnotification.fragments.home

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pushnotification.R
import com.example.pushnotification.fragments.home.HtmlCrawler.Companion.notices
import kotlinx.android.synthetic.main.item_loading.view.*
import kotlinx.android.synthetic.main.notice_list_item.view.*


class MyNoticeAdapter(context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    val context = context
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1

    // 아이템뷰에 게시물이 들어가는 경우
    inner class MyNoticeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title = itemView.txtNoticeTitle // 텍스트뷰의 값을 가져와 저장
        val info = itemView.txtNoticeInfo // 텍스트뷰의 값을 가져와 저장
    }

    // 아이템뷰에 프로그레스바가 들어가는 경우
    inner class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val progressBar = itemView.progressBar
    }

    // 뷰의 타입을 정해주는 곳이다.
    override fun getItemViewType(position: Int): Int {
        if(notices.get(position).title == " "){
            return  VIEW_TYPE_LOADING
        }
        return VIEW_TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View

        if (viewType === VIEW_TYPE_ITEM) {
            view = LayoutInflater.from(context).inflate(R.layout.notice_list_item, parent, false)
            return MyNoticeViewHolder(view)
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_loading, parent, false)
            return LoadingViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is MyNoticeViewHolder) {
            holder.title.text = notices.get(position).title
            holder.info.text = notices.get(position).info

            holder.itemView.setOnClickListener { v ->
                //var goUnivHomepage = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                var goUnivHomepage = Intent(v.context, WebViewActivity::class.java)
                var url = "http://www.anyang.ac.kr/bbs/boardView.do?bsIdx=61&bIdx=" +
                        notices.get(position).url + "&page=1&menuId=23&bcIdx=0&searchCondition=SUBJECT&searchKeyword="

                goUnivHomepage.putExtra("url", url)
                v.context.startActivity(goUnivHomepage);
            }
        } else if(holder is LoadingViewHolder){
            // 프로그레스바 있는곳
        }
    }

    override fun getItemCount(): Int {
        return notices.size
    }
}