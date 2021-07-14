package com.example.myapplication.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.database.ProductEntity
import kotlin.math.roundToLong

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

@BindingAdapter("setSelected")
fun setSelected(view: View, state: Boolean){
    view.isSelected = state
}

@BindingAdapter("set_amount")
fun TextView.setAmount(amount: Int){
    text = "$amount"
}

@BindingAdapter("saved")
fun TextView.savedMoney(product: ProductEntity){
    val saved = product.price - (product.price * (product.offer/100)).roundToLong()
    text = "its $saved saved"
}