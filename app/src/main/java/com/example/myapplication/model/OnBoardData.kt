package com.example.myapplication.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OnBoardData(
    val title: String,
    val description: String,
    val imgSource: Int
):Parcelable