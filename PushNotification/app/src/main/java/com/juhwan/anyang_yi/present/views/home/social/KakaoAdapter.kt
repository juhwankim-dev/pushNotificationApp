package com.juhwan.anyang_yi.present.views.home.social

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juhwan.anyang_yi.databinding.ItemKakaoBinding
import com.juhwan.anyang_yi.domain.model.Kakao
import com.juhwan.anyang_yi.present.views.home.WebViewActivity

class KakaoAdapter : RecyclerView.Adapter<KakaoAdapter.KakaoViewHolder>() {
    private val items = ArrayList<Kakao>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KakaoViewHolder {
        val binding = ItemKakaoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        notifyDataSetChanged()
    }

    inner class KakaoViewHolder(private val binding: ItemKakaoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(kakao: Kakao) {
            binding.kakao = kakao
            binding.clKakaoNotice.setOnClickListener {
                var goPage = Intent(it.context, WebViewActivity::class.java)
                goPage.putExtra("url", kakao.webLink)
                it.context.startActivity(goPage)
            }
        }
    }
}
