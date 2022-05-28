package com.juhwan.anyang_yi.present.views.home.notice.nonsubject

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.juhwan.anyang_yi.data.model.Apply
import com.juhwan.anyang_yi.databinding.ItemNonsubjectBinding
import com.juhwan.anyang_yi.present.views.home.WebViewActivity

class NonsubjectAdapter : RecyclerView.Adapter<NonsubjectAdapter.AllApplyViewHolder>() {
    private val items = ArrayList<Apply>()
    private val baseImageUrl = "http://ari.anyang.ac.kr"
    private val baseUrl = "https://ari.anyang.ac.kr/user/subject/nsubject/"
    private var arrangeValue = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllApplyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemNonsubjectBinding.inflate(layoutInflater, parent, false)
        return AllApplyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: AllApplyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setList(apply: List<Apply>) {
        items.addAll(apply)
    }

    fun arrangeList(position: Int) {
        if(position != arrangeValue) {
            items.reverse()
            notifyDataSetChanged()
            arrangeValue = position
        }
    }

    inner class AllApplyViewHolder(private val binding: ItemNonsubjectBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(apply: Apply) {
            binding.tvTitle.text = apply.title
            binding.tvTrainingPeriod.text = "기간: " + apply.trainingPeriod.substring(5, apply.trainingPeriod.length)
            binding.tvApplicant.text = "인원: " + apply.applicant

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