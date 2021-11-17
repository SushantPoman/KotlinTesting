package com.example.kotlintesting.global

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.kotlintesting.R

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String?) {

    try {
        if(url!=null)
        Glide.with(view)
            .load(url)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
            .into(view)
        else
            view.setImageResource(R.drawable.ic_launcher_foreground)
    } catch (e: Exception) {
        Logs.e("ImageException", e.toString())
    }
}