package com.example.myapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(
    val name: String,
    val description: String,
    val offer: Int,
    val categoryUrl: String,
):Parcelable