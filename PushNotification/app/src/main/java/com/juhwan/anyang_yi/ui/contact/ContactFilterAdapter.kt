package com.juhwan.anyang_yi.ui.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.data.Contact
import com.juhwan.anyang_yi.databinding.ItemContactFilterBinding
import com.juhwan.anyang_yi.repository.ContactRepository

class ContactFilterAdapter:
    RecyclerView.Adapter<ContactFilterAdapter.ContactFilterViewHolder>() {
    var items = ArrayList<Contact>()
    private val allItem = ContactRepository.contact

    init {
        items.addAll(allItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactFilterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemContactFilterBinding.inflate(layoutInflater, parent, false)
        return ContactFilterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ContactFilterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun filter(keyword: String) {
        items.clear()
        if (keyword.isEmpty()) {
            items.addAll(allItem)
        } else {
            for (contact in allItem) {
                if (contact.mClass.contains(keyword) || contact.sClass.contains(keyword) || contact.tel.replace("-", "").contains(keyword))
                    items.add(contact)
            }
        }
        notifyDataSetChanged()
    }

    fun selectDepartment(keyword: String){
        items.clear()

        if(keyword == ""){
            items.addAll(allItem)
        } else {
            for (contact in allItem) {
                if (contact.lClass == keyword)
                    items.add(contact)
            }
        }

        notifyDataSetChanged()
    }

    inner class ContactFilterViewHolder(private val binding: ItemContactFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) {

            var msClass = if (contact.mClass.isNotEmpty() && contact.sClass.isNotEmpty()) {
                contact.mClass + "・" + contact.sClass
            } else {
                contact.mClass + contact.sClass
            }

            binding.tvContact.text = msClass

            binding.tvContact.setOnClickListener {
                val dialog = ContactDialog(it.context)
                dialog.myDig(contact.lClass, msClass, contact.tel)
            }
        }
    }
}