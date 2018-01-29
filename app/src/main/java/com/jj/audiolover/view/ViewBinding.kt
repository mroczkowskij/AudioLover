package com.jj.audiolover.view

import android.databinding.BindingAdapter
import android.view.View

/**
 * Created by JJ on 29.01.2018.
 */
@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.GONE
}