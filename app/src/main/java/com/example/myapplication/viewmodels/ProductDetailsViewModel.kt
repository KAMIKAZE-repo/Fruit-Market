package com.example.myapplication.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.myapplication.database.ProductDataBase
import com.example.myapplication.model.ProductCard
import com.example.myapplication.model.toDataBaseModel
import com.example.myapplication.repositroies.FoodDatabaseRepo
import kotlinx.coroutines.launch

class ProductDetailsViewModel(application: Application): AndroidViewModel(application) {

    private val dataSource = ProductDataBase.getInstance(application)
    private val repository = FoodDatabaseRepo(dataSource)

    fun addToBuy(product: ProductCard){
        viewModelScope.launch {
            repository.addNewProductToBy(product.toDataBaseModel())
        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProductDetailsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ProductDetailsViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}