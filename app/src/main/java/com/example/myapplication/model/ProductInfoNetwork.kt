package com.example.myapplication.model

import com.squareup.moshi.Json

data class ProductInfoNetwork(
    val name: String,
    val price: Double,
    @Json(name = "photo_url")val photoUrl: String?,
    @Json(name = "category_url")val categoryUrl: String?,
    @Json(name = "vendor_url")val vendorUrl: String?
)


fun ProductInfoNetwork.toDomainModel():ProductCard{
    return ProductCard(
        "https://api.predic8.de$photoUrl" ?: "",
        name,
        price,
        false,
        0
    )
}