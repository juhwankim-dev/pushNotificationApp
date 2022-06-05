package com.juhwan.anyang_yi.present.views.home.notice.nonsubject

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.juhwan.anyang_yi.databinding.ItemNonsubjectBinding
import com.juhwan.anyang_yi.domain.model.Nonsubject
import com.juhwan.anyang_yi.present.views.home.WebViewActivity

class NonsubjectAdapter : RecyclerView.Adapter<NonsubjectAdapter.AllApplyViewHolder>() {
    private val items = ArrayList<Nonsubject>()
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

    fun setList(list: List<Nonsubject>) {
        items.addAll(list)
        notifyDataSetChanged()
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
        fun bind(nonsubject: Nonsubject) {
            binding.tvTitle.text = nonsubject.title
            binding.tvTrainingPeriod.text = nonsubject.trainingPeriod
            binding.tvApplicant.text = nonsubject.applicant

            binding.tvDDay.text = nonsubject.leftDay

            Glide.with(itemView.context).load(nonsubject.imageUrl).fitCenter()
                .apply(
                    RequestOptions.bitmapTransform(RoundedCorners(20))
                ).into(binding.ivThumbnail)

            binding.layoutApply.setOnClickListener {
                var goPage = Intent(it.context, WebViewActivity::class.java)

                goPage.putExtra("url", nonsubject.webLink)
                it.context.startActivity(goPage)
            }
        }

    }
}