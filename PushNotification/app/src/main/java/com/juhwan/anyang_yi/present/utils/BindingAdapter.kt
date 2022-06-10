package com.juhwan.anyang_yi.present.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.juhwan.anyang_yi.R

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("bindImageUrl")
    fun bindImageUrl(view: ImageView, src: String?){
        Glide.with(view.context)
            .load(src)
            .placeholder(R.drawable.no_image)
            .error(R.drawable.no_image)
            .fallback(R.drawable.no_image)
            .fitCenter()
            .apply(RequestOptions.bitmapTransform(RoundedCorners(20)))
            .into(view)
    }
}