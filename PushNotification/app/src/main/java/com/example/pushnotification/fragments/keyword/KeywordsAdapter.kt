package com.example.pushnotification.fragments.keyword

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pushnotification.R
import kotlinx.android.synthetic.main.keywords_list_item.view.*

class KeywordsAdapter(items: List<Keyword>, listener: OnItemClick) : RecyclerView.Adapter<KeywordsAdapter.KeywordsViewHolder>() {
    //val items = arrayListOf<String>()
    var items = items
    var mCallback = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = KeywordsViewHolder((parent)) // 뷰홀더 생성

    override fun getItemCount(): Int { // 아이템의 총 갯수 반환
        return items.size
    }

    override fun onBindViewHolder(holder: KeywordsViewHolder, position: Int) { // 생선된 뷰홀더에 데이터 삽입
        holder.keyword.text = items.get(position).keyword
        holder.btnDelete.setOnClickListener {
            mCallback.onClick(items.get(position).keyword)
        }
    }

    override fun getItemId(position: Int): Long {
        return items.get(position).hashCode().toLong()
    }

    inner class KeywordsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.keywords_list_item, parent, false)){

        val keyword = itemView.txt_keyword
        val btnDelete = itemView.btn_delete
    }
}