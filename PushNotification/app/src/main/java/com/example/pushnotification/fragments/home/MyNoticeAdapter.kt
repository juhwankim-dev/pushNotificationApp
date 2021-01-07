package com.example.pushnotification.fragments.home

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.pushnotification.R
import com.example.pushnotification.fragments.home.HtmlCrawler.Companion.notices
import kotlinx.android.synthetic.main.notice_list_item.view.*

class MyNoticeAdapter : RecyclerView.Adapter<MyNoticeAdapter.MyNoticeViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyNoticeViewHolder((parent))

    override fun getItemCount(): Int {
        return notices.size
    }

    override fun onBindViewHolder(holder: MyNoticeAdapter.MyNoticeViewHolder, position: Int) {
        holder.title.text = notices.get(position).title
        holder.info.text = notices.get(position).info

        holder.itemView.setOnClickListener {v->
            //var goUnivHomepage = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            var goUnivHomepage = Intent(v.context, WebViewActivity::class.java)
            var url = "http://www.anyang.ac.kr/bbs/boardView.do?bsIdx=61&bIdx=" +
                    notices.get(position).url + "&page=1&menuId=23&bcIdx=0&searchCondition=SUBJECT&searchKeyword="

            goUnivHomepage.putExtra("url", url)
            v.context.startActivity(goUnivHomepage);
        }
    }

    inner class MyNoticeViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.notice_list_item, parent, false)){

        val title = itemView.txtNoticeTitle // 텍스트뷰의 값을 가져와 저장
        val info = itemView.txtNoticeInfo // 텍스트뷰의 값을 가져와 저장
    }
}