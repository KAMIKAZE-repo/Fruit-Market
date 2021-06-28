package com.example.myapplication.model

import com.squareup.moshi.Json

data class CategoryNetwork(
    val name: String?,
    @Json(name = "category_url") val categoryUrl: String
)