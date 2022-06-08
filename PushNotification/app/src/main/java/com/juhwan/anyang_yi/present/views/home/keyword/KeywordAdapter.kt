package com.juhwan.anyang_yi.present.views.home.keyword

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.data.model.KeywordEntity
import com.juhwan.anyang_yi.databinding.ItemKeywordBinding

class KeywordAdapter(listener: DeleteButtonListener) : RecyclerView.Adapter<KeywordAdapter.KeywordsViewHolder>() {
    private val items = ArrayList<KeywordEntity>()
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

    fun setList(list: List<KeywordEntity>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class KeywordsViewHolder(private val binding: ItemKeywordBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(keywordEntityList: KeywordEntity){
            binding.tvKeyword.text = keywordEntityList.keyword
            binding.ivDelete.setOnClickListener {
                mCallback.unSubscribe(keywordEntityList.keyword)
            }
        }
    }
}