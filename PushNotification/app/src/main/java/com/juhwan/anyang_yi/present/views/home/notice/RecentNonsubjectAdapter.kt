package com.juhwan.anyang_yi.present.views.home.notice

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.juhwan.anyang_yi.databinding.ItemRecentNonsubjectBinding
import com.juhwan.anyang_yi.domain.model.Nonsubject
import com.juhwan.anyang_yi.present.views.home.WebViewActivity

class RecentNonsubjectAdapter : RecyclerView.Adapter<RecentNonsubjectAdapter.ApplyViewHolder>() {
    private val items = ArrayList<Nonsubject>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecentNonsubjectBinding.inflate(layoutInflater, parent, false)
        return ApplyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ApplyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setList(nonsubjectEntity: List<Nonsubject>) {
        items.addAll(nonsubjectEntity)
    }

    inner class ApplyViewHolder(private val binding: ItemRecentNonsubjectBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(nonsubject: Nonsubject) {
            binding.tvTitle.text = nonsubject.title
            binding.tvDDay.text = nonsubject.leftDay

            Glide.with(itemView.context).load(nonsubject.imageUrl).fitCenter()
                .apply(
                    RequestOptions.bitmapTransform(RoundedCorners(20))
                ).into(binding.ivThumbnail)

            binding.clNonsubject.setOnClickListener {
                var goPage = Intent(it.context, WebViewActivity::class.java)

                goPage.putExtra("url", nonsubject.webLink)
                it.context.startActivity(goPage)
            }
        }

    }
}
