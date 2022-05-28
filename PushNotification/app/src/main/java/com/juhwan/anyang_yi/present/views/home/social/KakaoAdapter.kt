package com.juhwan.anyang_yi.present.views.home.social

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.juhwan.anyang_yi.R
import com.juhwan.anyang_yi.data.model.Item
import com.juhwan.anyang_yi.databinding.ItemKakaoBinding
import com.juhwan.anyang_yi.data.repository.InitialRepository
import com.juhwan.anyang_yi.present.views.home.WebViewActivity
import java.text.SimpleDateFormat

class KakaoAdapter(items: ArrayList<Item>) : RecyclerView.Adapter<KakaoAdapter.KakaoViewHolder>() {
    private val items = ArrayList<Item>()
    private val sdf = SimpleDateFormat("yyyy-MM-dd")

    init {
        this.items.addAll(items.subList(0, 10))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KakaoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemKakaoBinding.inflate(layoutInflater, parent, false)
        return KakaoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: KakaoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class KakaoViewHolder(private val binding: ItemKakaoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(kakaoNotice: Item) {
            var date = sdf.format(kakaoNotice.created_at)
            binding.tvTitle.text = kakaoNotice.title
            binding.tvDate.text = date

            var hms2 = "$date 00:00:00"
            var writeDate = InitialRepository.sf.parse(hms2)
            var calculateDate = (InitialRepository.todayDate.time - writeDate.time) / (60 * 60 * 24 * 1000)

            if(calculateDate.toInt() < 2){
                binding.ivNew.visibility = View.VISIBLE
            } else {
                binding.ivNew.visibility = View.GONE
            }

            try{
                if(kakaoNotice.media[0].small_url.isNotEmpty()){
                    Glide.with(itemView.context).load(kakaoNotice.media[0].small_url).fitCenter()
                        .apply(
                            RequestOptions.bitmapTransform(RoundedCorners(20))
                        ).into(binding.ivThumbnail)
                } else {
                    setDefaultImage()
                }
            } catch (e: Exception){
                setDefaultImage()
            }

            binding.clKakaoNotice.setOnClickListener {
                var goPage = Intent(it.context, WebViewActivity::class.java)

                goPage.putExtra("url", kakaoNotice.permalink)
                it.context.startActivity(goPage)
            }
        }

        fun setDefaultImage(){
            Glide.with(itemView.context).load(R.drawable.no_image).fitCenter()
                .apply(
                    RequestOptions.bitmapTransform(RoundedCorners(20))
                ).into(binding.ivThumbnail)
        }
    }
}
