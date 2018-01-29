package com.jj.audiolover.view

import com.bumptech.glide.Glide
import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions


/**
 * Created by JJ on 28.01.2018.
 */

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    val context = imageView.getContext()
    Glide.with(context)
            .load(url)
            .apply(RequestOptions.centerCropTransform())
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imageView)
}