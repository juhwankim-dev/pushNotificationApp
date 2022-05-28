package com.juhwan.anyang_yi.present.views.home.keyword

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.data.db.Keyword
import com.juhwan.anyang_yi.databinding.ItemKeywordBinding

class KeywordAdapter(listener: DeleteButtonListener) : RecyclerView.Adapter<KeywordAdapter.KeywordsViewHolder>() {
    private val items = ArrayList<Keyword>()
    private val mCallback = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : KeywordsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemKeywordBinding.inflate(layoutInflater, parent, false)
        return KeywordsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: KeywordsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setList(it: List<Keyword>) {
        items.clear()
        items.addAll(it)
    }

    inner class KeywordsViewHolder(private val binding: ItemKeywordBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(keywordList: Keyword){
            binding.tvKeyword.text = keywordList.keyword
            binding.btnDelete.setOnClickListener {
                mCallback.unSubscribe(keywordList.keyword)
            }
        }
    }
}