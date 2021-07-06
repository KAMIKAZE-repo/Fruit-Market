package com.example.myapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products_to_buy")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    var productId: Long = 0L,

    @ColumnInfo(name = "product_name")
    val name: String,

    @ColumnInfo(name = "product_price")
    val price: Double,

    @ColumnInfo(name = "product_offer")
    val offer: Int,

    @ColumnInfo(name = "product_img_url")
    val imgUrl: String,

    @ColumnInfo(name = "product_amount")
    var amount: Int = 1,
)

