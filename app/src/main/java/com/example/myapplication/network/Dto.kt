package com.example.myapplication.network

import com.example.myapplication.model.Category
import com.example.myapplication.model.CategoryNetwork


data class FoodCategoriesContainer(
    val categories: List<CategoryNetwork>
)

fun FoodCategoriesContainer.toDomainModel():List<Category>{
    return categories.map{
        Category(
            it.name ?: "",
            "Pick up from organic farms",
            20
        )
    }
}