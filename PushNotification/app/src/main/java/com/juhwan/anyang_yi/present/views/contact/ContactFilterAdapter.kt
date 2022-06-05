package com.juhwan.anyang_yi.present.views.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.databinding.ItemContactFilterBinding
import com.juhwan.anyang_yi.domain.model.Department

class ContactFilterAdapter:
    RecyclerView.Adapter<ContactFilterAdapter.ContactFilterViewHolder>() {
    var allList = ArrayList<Department>()
    var filteredList = ArrayList<Department>()

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

    fun setList(list: List<Department>) {
        allList.addAll(list)
    }

    fun filter(keyword: String) {
        filteredList.clear()
        if (keyword.isEmpty()) {
            filteredList.addAll(allList)
        } else {
            for (contact in allList) {
                if (contact.mClass.contains(keyword) || contact.sClass.contains(keyword) || contact.tel.replace("-", "").contains(keyword))
                    filteredList.add(contact)
            }
        }
        notifyDataSetChanged()
    }

    fun selectDepartment(keyword: String){
        filteredList.clear()

        if(keyword == ""){
            filteredList.addAll(allList)
        } else {
            for (contact in allList) {
                if (contact.lClass == keyword)
                    filteredList.add(contact)
            }
        }

        notifyDataSetChanged()
    }

    inner class ContactFilterViewHolder(private val binding: ItemContactFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(department: Department) {

            var msClass = if (department.mClass.isNotEmpty() && department.sClass.isNotEmpty()) {
                department.mClass + "・" + department.sClass
            } else {
                department.mClass + department.sClass
            }

            binding.tvContact.text = msClass

            binding.tvContact.setOnClickListener {
                val dialog = ContactDialog(it.context)
                dialog.myDig(department.lClass, msClass, department.tel)
            }
        }
    }
}