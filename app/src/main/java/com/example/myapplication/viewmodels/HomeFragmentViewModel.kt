package com.example.myapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.ProductCard
import com.example.myapplication.utils.chipsData
import com.example.myapplication.utils.products

class HomeFragmentViewModel: ViewModel() {

    private val _categories = MutableLiveData<List<String>>()
    val categories: LiveData<List<String>>
        get() = _categories

    init {
        _categories.value = chipsData
    }

    companion object{
        fun getCategoryProducts(category: String):List<ProductCard>{
            return products
        }

        fun getProduct(id: Int){

        }
    }
}