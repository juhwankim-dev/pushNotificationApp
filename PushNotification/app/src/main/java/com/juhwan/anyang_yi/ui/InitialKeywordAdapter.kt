package com.juhwan.anyang_yi.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.data.InitialKeyword
import com.juhwan.anyang_yi.databinding.ItemInitialKeywordBinding

/*
class InitialKeywordAdapter(
    items: ArrayList<InitialKeyword>,
    listener: InitialActivity
) : RecyclerView.Adapter<InitialKeywordAdapter.InitialKeywordViewHolder>() {
    private val items = items
    private val mCallback = listener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InitialKeywordViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemInitialKeywordBinding.inflate(layoutInflater, parent, false)
        return InitialKeywordViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: InitialKeywordViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class InitialKeywordViewHolder(private val binding: ItemInitialKeywordBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(items: InitialKeyword) {
            binding.tvKeyword.text = items.keyword

            binding.tvKeyword.setOnClickListener {
                when(items.isSelected){
                    true -> {
                        binding.tvKeyword.setBackgroundResource(R.drawable.button_outline)
                        binding.tvKeyword.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorBlackDisabled2))
                        items.isSelected = false
                        mCallback.keywordUnSelect(items.keyword)
                    }
                    false -> {
                        binding.tvKeyword.setBackgroundResource(R.drawable.button_blue_round_fill)
                        binding.tvKeyword.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorWhite))
                        items.isSelected = true
                        mCallback.keywordSelect(items.keyword)
                    }
                }
            }
        }
    }
}*/
