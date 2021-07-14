package com.example.myapplication.repositroies

import android.util.Log
import com.example.myapplication.database.ProductEntity
import com.example.myapplication.model.*
import com.example.myapplication.network.FoodApi
import com.example.myapplication.network.toDomainModel
import okhttp3.ResponseBody
import retrofit2.Response

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

    suspend fun createNewOrder(customerId: Long): OrderDetails{
        return FoodApi.retrofitService.createOrderAsync(customerId).await()
    }

    suspend fun addNewProductToOrders(orderId: Long, product: ProductEntity): ResponseItem {
        val item = Item(
            product.amount,
            product.price,
            "/",
            "/shop/products/${product.productId}"
        )
        Log.i("TAG","$item, $orderId")
        return FoodApi.retrofitService.placeOrderAsync(orderId, item).await()
    }
}