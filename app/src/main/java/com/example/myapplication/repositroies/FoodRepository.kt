package com.example.myapplication.repositroies

import androidx.lifecycle.LiveData
import com.example.myapplication.database.ProductDataBase
import com.example.myapplication.database.ProductEntity
import com.example.myapplication.model.*
import com.example.myapplication.network.FoodApi
import com.example.myapplication.network.toDomainModel

class FoodRepository{

    suspend fun getProductInfo(id: Int): ProductCard {
        return FoodApi.retrofitService.getProductInfoAsync(id).await().toDomainModel()
    }


    suspend fun getAllCategories():List<Category>{
       val categories =   FoodApi.retrofitService.getAllCategoriesAsync().await()
        return categories.toDomainModel()
    }

    suspend fun getAllProductByCategory(name: String):List<Product>{
        return FoodApi.retrofitService.getAllProductsAsync(name).await().products
    }
}