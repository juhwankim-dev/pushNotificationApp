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
import com.juhwan.anyang_yi.databinding.ItemKakaoBinding
import com.juhwan.anyang_yi.domain.model.Kakao
import com.juhwan.anyang_yi.present.views.home.WebViewActivity

class KakaoAdapter : RecyclerView.Adapter<KakaoAdapter.KakaoViewHolder>() {
    private val items = ArrayList<Kakao>()

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

    fun setList(list: List<Kakao>) {
        items.clear()
        items.addAll(list)
    }

    inner class KakaoViewHolder(private val binding: ItemKakaoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(kakaoNotice: Kakao) {
            binding.tvTitle.text = kakaoNotice.title
            binding.tvDate.text = kakaoNotice.date

            if(kakaoNotice.isNew){
                binding.ivNew.visibility = View.VISIBLE
            } else {
                binding.ivNew.visibility = View.GONE
            }

            try{
                Glide.with(itemView.context).load(kakaoNotice.url).fitCenter()
                    .apply(
                        RequestOptions.bitmapTransform(RoundedCorners(20))
                    ).into(binding.ivThumbnail)
            } catch (e: Exception){
                setDefaultImage()
            }

            binding.clKakaoNotice.setOnClickListener {
                var goPage = Intent(it.context, WebViewActivity::class.java)

                goPage.putExtra("url", kakaoNotice.webLink)
                it.context.startActivity(goPage)
            }
        }

        private fun setDefaultImage(){
            Glide.with(itemView.context).load(R.drawable.no_image).fitCenter()
                .apply(
                    RequestOptions.bitmapTransform(RoundedCorners(20))
                ).into(binding.ivThumbnail)
        }
    }
}
