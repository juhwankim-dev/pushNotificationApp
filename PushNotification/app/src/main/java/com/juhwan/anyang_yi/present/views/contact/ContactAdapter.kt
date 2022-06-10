package com.juhwan.anyang_yi.present.views.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ItemContactBinding
import com.juhwan.anyang_yi.domain.model.Contact

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    private lateinit var itemClickListener: ItemClickListener
    var items = ArrayList<String>()
    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    fun setList(list: List<Contact>) {
        items.clear()
        items.add("전체")
        list.forEach {
            if(!items.contains(it.category) && it.category != null) {
                items.add(it.category)
            }
        }
        notifyDataSetChanged()
    }

    inner class ContactViewHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: String, position: Int) {
            binding.category = if (position == 0) {
                "전체"
            } else {
                category
            }

            binding.layoutContact.setOnClickListener {
                selectedPosition = position
                itemClickListener.onClick(category)
            }

            if (selectedPosition == position) {
                binding.layoutContact.setBackgroundResource(R.color.colorWhite)
                binding.tvCategory.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorBlack))
            } else {
                binding.layoutContact.setBackgroundResource(R.color.lightGray)
                binding.tvCategory.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorBlackDisabled2))
            }
        }
    }

    interface ItemClickListener {
        fun onClick(category: String)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}