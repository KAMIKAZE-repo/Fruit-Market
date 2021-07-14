    package com.example.myapplication.network

import com.example.myapplication.model.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

private const val BASE_URL = "https://api.predic8.de:443/shop/"

private val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()

private val retrofit = Retrofit.Builder()
                        .addConverterFactory(MoshiConverterFactory.create(moshi))
                        .addCallAdapterFactory(CoroutineCallAdapterFactory())
                        .baseUrl(BASE_URL)
                        .build()

interface FoodService{
    @GET("categories/")
    fun getAllCategoriesAsync():Deferred<FoodCategoriesContainer>

    @GET("categories/{name}")
    fun getAllProductsAsync(@Path("name")name: String): Deferred<ProductListContainer>

    @GET("products/{id}")
    fun getProductInfoAsync(@Path("id")id: Int):Deferred<ProductInfoNetwork>

    @GET("customers/{customer_id}")
    fun getCustomerAsync(@Path("customer_id") id: Long): Deferred<Customer>

    @GET("customers/{customer_id}/orders/")
    fun getCustomerOrderAsync(@Path("customer_id") id: Long): Deferred<OrdersContainer>

    @GET("orders/{order_id}")
    fun getOrderDetailsAsync(@Path("order_id") id: Long): Deferred<OrderDetails>

    @GET("orders/{order_id}/items/")
    fun getAllItemsDetailsAsync(@Path("order_id") id: Long): Deferred<Items>

    //post orders to server
    @POST("customers/{customer_id}/orders/")
    fun createOrderAsync(@Path("customer_id")id: Long): Deferred<OrderDetails>

    @POST("orders/{order_id}/items/")
    fun placeOrderAsync(@Path("order_id")id: Long, @Body item: Item): Deferred<ResponseItem>

}

object FoodApi{
    val retrofitService: FoodService by lazy {
        retrofit.create(FoodService::class.java)
    }
}