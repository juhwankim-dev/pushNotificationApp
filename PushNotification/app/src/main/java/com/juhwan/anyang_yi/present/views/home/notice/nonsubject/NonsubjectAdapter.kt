package com.juhwan.anyang_yi.present.views.home.notice.nonsubject

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.databinding.ItemNonsubjectBinding
import com.juhwan.anyang_yi.domain.model.Nonsubject
import com.juhwan.anyang_yi.present.views.home.WebViewActivity

class NonsubjectAdapter : RecyclerView.Adapter<NonsubjectAdapter.AllApplyViewHolder>() {
    private val items = ArrayList<Nonsubject>()
    private var sortOption = RECENT_ORDER

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllApplyViewHolder {
        val binding = ItemNonsubjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllApplyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: AllApplyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setList(list: List<Nonsubject>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    fun sortList(position: Int) {
        if(position != sortOption) {
            items.reverse()
            notifyDataSetChanged()
            sortOption = position
        }
    }

    inner class AllApplyViewHolder(private val binding: ItemNonsubjectBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(nonsubject: Nonsubject) {
            binding.nonsubject = nonsubject
            binding.layoutApply.setOnClickListener {
                var goPage = Intent(it.context, WebViewActivity::class.java)
                goPage.putExtra("url", nonsubject.webLink)
                it.context.startActivity(goPage)
            }
        }
    }

    companion object {
        val RECENT_ORDER = 0
        val DEADLINE_ORDER = 1
    }
}