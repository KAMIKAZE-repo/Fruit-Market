package com.example.myapplication.model

import android.os.Parcelable
import com.example.myapplication.database.ProductEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductCard(
    val imgUrl: String,
    val name: String,
    val price: Double,
    val favorite: Boolean,
    var offer: Int
):Parcelable


fun ProductCard.toDataBaseModel():ProductEntity{
    return ProductEntity(
        0,
        name,
        price,
        offer,
        imgUrl,
        1
    )
}