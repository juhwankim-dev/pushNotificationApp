package com.juhwan.anyang_yi.present.views.home.notice

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.databinding.ItemRecentNonsubjectBinding
import com.juhwan.anyang_yi.domain.model.Nonsubject
import com.juhwan.anyang_yi.present.views.home.WebViewActivity

class RecentNonsubjectAdapter : RecyclerView.Adapter<RecentNonsubjectAdapter.ApplyViewHolder>() {
    private val items = ArrayList<Nonsubject>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplyViewHolder {
        val binding = ItemRecentNonsubjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ApplyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ApplyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setList(list: List<Nonsubject>) {
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class ApplyViewHolder(private val binding: ItemRecentNonsubjectBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(nonsubject: Nonsubject) {
            binding.nonsubject = nonsubject

            binding.clNonsubject.setOnClickListener {
                var goPage = Intent(it.context, WebViewActivity::class.java)
                goPage.putExtra("url", nonsubject.webLink)
                it.context.startActivity(goPage)
            }
        }

    }
}
