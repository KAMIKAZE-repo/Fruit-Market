package com.example.myapplication.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("offer")
fun TextView.setOffer(offer: Int){
    text = "($offer%)"
}

@BindingAdapter("price")
fun TextView.setProductPrice(price: Double){
    text = "$ $price Per/Kg"
}