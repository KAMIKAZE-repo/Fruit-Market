package com.example.myapplication.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.example.myapplication.model.ProductCard

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewOrder(order: ProductEntity)

    @Query("SELECT * FROM products_to_buy")
    fun getAllProductsToBuy(): LiveData<List<ProductEntity>>

    @Update
    fun updateAmount(product: ProductEntity)

    @Delete
    fun deleteProduct(product: ProductEntity)

    @Query("DELETE FROM products_to_buy")
    fun deleteAllProducts()
}