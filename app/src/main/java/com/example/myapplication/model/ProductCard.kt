package com.example.myapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductCard(
    val imgUrl: String,
    val name: String,
    val price: Double,
    val favorite: Boolean
):Parcelable