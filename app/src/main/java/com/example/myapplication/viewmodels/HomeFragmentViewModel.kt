package com.example.myapplication.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Category
import com.example.myapplication.model.Product
import com.example.myapplication.model.ProductCard
import com.example.myapplication.repositroies.FoodRepository
import com.example.myapplication.utils.chipsData
import com.example.myapplication.utils.products
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class HomeFragmentViewModel: ViewModel() {

    private val repository = FoodRepository()

    private val _chips = MutableLiveData<List<String>>()
    val chips: LiveData<List<String>>
        get() = _chips

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>>
        get() = _categories


    init {
        _chips.value = chipsData
        viewModelScope.launch {
            _categories.value = repository.getAllCategories()
        }
    }

    companion object{
        fun getCategoryProducts(category: String):List<ProductCard>{
            return products.getOrElse(category){ listOf()}
        }

        fun getProduct(id: Int){

        }
    }


    //TODO("remove those blocking Thread Functions")
    fun getAllProducts(name: String):List<Product> = runBlocking {
        try {
            val listProduct: List<Product>
            withContext(Dispatchers.IO){
                listProduct = repository.getAllProductByCategory(name)
            }
            listProduct
        }catch (e:Exception){
            listOf()
        }
    }

    fun getProductInfo(id: Int):ProductCard = runBlocking(Dispatchers.IO) {
            repository.getProductInfo(id)
    }

}