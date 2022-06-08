package com.juhwan.anyang_yi.present.views.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.databinding.ItemContactFilterBinding
import com.juhwan.anyang_yi.domain.model.Contact

class ContactFilterAdapter:
    RecyclerView.Adapter<ContactFilterAdapter.ContactFilterViewHolder>() {
    private lateinit var itemClickListener: ItemClickListener
    var allList = ArrayList<Contact>()
    var filteredList = ArrayList<Contact>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactFilterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemContactFilterBinding.inflate(layoutInflater, parent, false)
        return ContactFilterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun onBindViewHolder(holder: ContactFilterViewHolder, position: Int) {
        holder.bind(filteredList[position])
    }

    fun setList(list: List<Contact>) {
        allList.clear()
        allList.addAll(list)
        selectDepartment("")
        notifyDataSetChanged()
    }

    fun search(keyword: String) {
        filteredList.clear()
        if (keyword.isEmpty()) {
            filteredList.addAll(allList)
        } else {
            allList.forEach {
                if(it.department != null && it.department.contains(keyword)
                    || it.job != null && it.job.contains(keyword)
                    || it.tel != null && it.tel.replace("-", "").contains(keyword)) {
                    filteredList.add(it)
                }
            }
        }
        notifyDataSetChanged()
    }

    fun selectDepartment(keyword: String){
        filteredList.clear()
        if(keyword == ""){
            filteredList.addAll(allList)
        } else {
            filteredList.addAll(allList.filter { it.category == keyword })
        }
        notifyDataSetChanged()
    }

    inner class ContactFilterViewHolder(private val binding: ItemContactFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) {
            binding.tvSubCategory.text = contact.subCategory

            binding.tvSubCategory.setOnClickListener {
                itemClickListener.onClick(contact)
            }
        }
    }

    interface ItemClickListener {
        fun onClick(contact: Contact)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}