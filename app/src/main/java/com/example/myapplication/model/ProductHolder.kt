package com.example.myapplication.model

data class ProductHolder(
    val category: Category,
    val products: List<Product>,
    val productsCards: MutableList<ProductCard>
)