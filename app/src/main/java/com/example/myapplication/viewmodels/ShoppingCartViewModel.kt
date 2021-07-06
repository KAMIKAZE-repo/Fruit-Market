package com.example.myapplication.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.myapplication.database.ProductDataBase
import com.example.myapplication.database.ProductEntity
import com.example.myapplication.repositroies.FoodDatabaseRepo
import kotlinx.coroutines.launch

class ShoppingCartViewModel(app: Application): AndroidViewModel(app) {

    private val dataSource = ProductDataBase.getInstance(app)
    private val repository = FoodDatabaseRepo(dataSource)

    val productList = repository.productsToBuy


    fun updateAmount(value: Int, product: ProductEntity){
        //product.amount += value
        val newProduct = ProductEntity(
            product.productId,
            product.name,
            product.price,
            product.offer,
            product.imgUrl,
            product.amount + value
        )
        viewModelScope.launch {
            repository.updateProductAmount(newProduct)
        }
    }

    fun deleteProduct(product: ProductEntity){
        viewModelScope.launch {
            repository.deleteProductOrder(product)
        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ShoppingCartViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ShoppingCartViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}