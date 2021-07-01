package com.example.myapplication.utils

import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.myapplication.R

@BindingAdapter("offer")
fun TextView.setOffer(offer: Int){
    text = "($offer%)"
}

@BindingAdapter("price")
fun TextView.setProductPrice(price: Double){
    text = "$ $price Per/Kg"
}

@BindingAdapter("loadImageUrl")
fun loadUrlImg(imageView: ImageView, url: String?){
    url?.let {
        val imgURI = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imgURI)
            .into(imageView)
    }
    if(url == null){
        imageView.setImageResource(R.drawable.onboard_img_1)
    }
}