package com.juhwan.anyang_yi.ui.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.databinding.ItemContactBinding
import com.juhwan.anyang_yi.repository.ContactRepository

class ContactAdapter(listener: ContactFragment) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    var mCallback = listener
    var items = ContactRepository.departmentList
    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(layoutInflater, parent, false)
        return ContactViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    inner class ContactViewHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(str: String, position: Int) {
            if (position == 0) {
                binding.tvContact.text = "전체"
            } else {
                binding.tvContact.text = str
            }

            binding.layoutContact.setOnClickListener {
                selectedPosition = position
                notifyDataSetChanged()

                mCallback.selectDepartment(str)
            }

            if (selectedPosition == position) {
                binding.layoutContact.setBackgroundResource(R.color.colorWhite)
                binding.tvContact.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorBlack))
            } else {
                binding.layoutContact.setBackgroundResource(R.color.lightGray)
                binding.tvContact.setTextColor(ContextCompat.getColor(itemView.context, R.color.colorBlackDisabled2))
            }
        }
    }
}