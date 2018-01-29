package com.jj.audiolover.view

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView

/**
 * Created by JJ on 28.01.2018.
 */

@BindingAdapter("scrollListener")
fun setScrollListener(recyclerView: RecyclerView, listener: RecyclerView.OnScrollListener) {
    recyclerView.addOnScrollListener(listener)
}