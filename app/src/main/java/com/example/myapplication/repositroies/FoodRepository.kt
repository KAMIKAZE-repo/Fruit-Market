package com.example.myapplication.repositroies

import android.util.Log
import com.example.myapplication.network.FoodApi
import com.example.myapplication.network.FoodCategoriesContainer

class FoodRepository {
    suspend fun getAllCategories(): FoodCategoriesContainer {
        return FoodApi.retrofitService.getAllCategoriesAsync().await()
    }

    suspend fun getAllCustomers(){
        val container = FoodApi.retrofitService.getAllCustomersAsync().await()
        Log.i("TAG", container.customers.toString())
    }
}