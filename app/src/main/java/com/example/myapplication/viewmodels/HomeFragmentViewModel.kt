package com.example.myapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.ProductCard
import com.example.myapplication.repositroies.FoodRepository
import com.example.myapplication.utils.chipsData
import com.example.myapplication.utils.products
import kotlinx.coroutines.launch

class HomeFragmentViewModel: ViewModel() {

    private val repository = FoodRepository()

    private val _categories = MutableLiveData<List<String>>()
    val categories: LiveData<List<String>>
        get() = _categories

    init {
        _categories.value = chipsData
        viewModelScope.launch {
           repository.getAllCustomers()
        }
    }

    companion object{
        fun getCategoryProducts(category: String):List<ProductCard>{
            return products.getOrElse(category){ listOf()}
        }

        fun getProduct(id: Int){

        }
    }
}