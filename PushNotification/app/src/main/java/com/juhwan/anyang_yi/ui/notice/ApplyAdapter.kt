package com.juhwan.anyang_yi.ui.notice

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.juhwan.anyang_yi.data.Apply
import com.juhwan.anyang_yi.databinding.ItemApplyBinding

class ApplyAdapter : RecyclerView.Adapter<ApplyAdapter.ApplyViewHolder>() {
    private val items = ArrayList<Apply>()
    private val baseImageUrl = "http://ari.anyang.ac.kr"
    private val baseUrl = "https://ari.anyang.ac.kr/user/subject/nsubject/"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemApplyBinding.inflate(layoutInflater, parent, false)
        return ApplyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ApplyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setList(apply: List<Apply>) {
        items.addAll(apply)
    }

    inner class ApplyViewHolder(private val binding: ItemApplyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(apply: Apply) {
            binding.tvTitle.text = apply.title
            binding.tvDDay.text = apply.dDay

            Glide.with(itemView.context).load(baseImageUrl + apply.imageUrl).fitCenter()
                .apply(
                    RequestOptions.bitmapTransform(RoundedCorners(20))
                ).into(binding.ivThumbnail)

            binding.layoutApply.setOnClickListener {
                var goPage = Intent(it.context, WebViewActivity::class.java)

                goPage.putExtra("url", baseUrl + apply.idx)
                it.context.startActivity(goPage)
            }
        }

    }
}
