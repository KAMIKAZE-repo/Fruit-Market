package com.example.myapplication.model

import com.squareup.moshi.Json

data class ProductListContainer(
    val name: String,
    val products: List<Product>
)

data class Product(
    val name: String,
    @Json(name = "product_url")val productUrl: String
)


fun ProductListContainer.toDomainModel() = products