package com.example.myapplication.network

import com.example.myapplication.model.Category
import com.example.myapplication.model.CategoryNetwork
import com.example.myapplication.model.CustomersContainer
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

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

    @GET("customers/")
    fun getAllCustomersAsync(): Deferred<CustomersContainer>
}

object FoodApi{
    val retrofitService: FoodService by lazy {
        retrofit.create(FoodService::class.java)
    }
}