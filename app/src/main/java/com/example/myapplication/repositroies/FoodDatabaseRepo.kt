package com.example.myapplication.repositroies

import androidx.lifecycle.LiveData
import com.example.myapplication.database.ProductDataBase
import com.example.myapplication.database.ProductEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodDatabaseRepo(private val dataSource: ProductDataBase){

    fun getAllProductToBuy(): LiveData<List<ProductEntity>> {
        return dataSource.productDao.getAllProductsToBuy()
    }

    suspend fun addNewProductToBy(product: ProductEntity){
        withContext(Dispatchers.IO){
            dataSource.productDao.insertNewOrder(product)
        }
    }

    suspend fun updateProductAmount(product: ProductEntity){
        dataSource.productDao.updateAmount(product)
    }

    suspend fun deleteProductOrder(product: ProductEntity){
        dataSource.productDao.deleteProduct(product)
    }
}