package com.example.myapplication.repositroies

import com.example.myapplication.database.ProductDataBase
import com.example.myapplication.database.ProductEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodDatabaseRepo(private val dataSource: ProductDataBase){


    val productsToBuy by lazy {dataSource.productDao.getAllProductsToBuy()}


    suspend fun addNewProductToBy(product: ProductEntity){
        withContext(Dispatchers.IO){
            dataSource.productDao.insertNewOrder(product)
        }
    }

    suspend fun updateProductAmount(product: ProductEntity){
        withContext(Dispatchers.IO){
            dataSource.productDao.updateAmount(product)
        }
    }

    suspend fun deleteProductOrder(product: ProductEntity){
        withContext(Dispatchers.IO){
            dataSource.productDao.deleteProduct(product)
        }
    }
}