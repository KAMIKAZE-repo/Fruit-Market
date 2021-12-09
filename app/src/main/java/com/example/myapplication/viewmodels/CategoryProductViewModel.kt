package com.example.myapplication.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.myapplication.model.Category
import com.example.myapplication.model.FavoriteProduct
import com.example.myapplication.model.ProductCard
import com.example.myapplication.repositroies.FoodRepository
import com.example.myapplication.utils.favoriteProducts
import kotlinx.coroutines.launch

class CategoryProductViewModel(app: Application, category: Category) : AndroidViewModel(app) {

    private val repository = FoodRepository()

    private val _listProduct = MutableLiveData<List<ProductCard>>()
    val listProduct: LiveData<List<ProductCard>>
        get() = _listProduct

    init {
        viewModelScope.launch {
            getData(category)
        }
    }

    fun addToFavorite(pos: Int) {
        val newData = mutableListOf<ProductCard>()
        _listProduct.value?.let { newData.addAll(it) }
        with(newData[pos].copy(favorite = true)) {
            newData[pos] = this
            favoriteProducts.add(
                FavoriteProduct(
                    this
                )
            )
        }
        _listProduct.value = newData
    }

    private suspend fun getData(category: Category) {
        val products = repository.getAllProductByCategory(category.name).toMutableList()
        val productsCards = mutableListOf<ProductCard>()
        //forEach product we sent a request to get ProductInfo
        products.forEachIndexed { _, product ->
            val productCard = repository.getProductInfo(product.id)
            productCard.offer = category.offer
            productCard.productId = product.id.toLong()
            productsCards.add(productCard)
        }
        _listProduct.value = productsCards
    }

    fun sortProduct(sortOption: Boolean) {
        var newData = mutableListOf<ProductCard>()
        _listProduct.value?.let { newData.addAll(it) }
        if (sortOption) {
            newData.shuffle()

        } else {
            newData = newData.sortedBy {
                it.price
            } as MutableList<ProductCard>
        }
        _listProduct.value = newData
    }

    class Factory(val app: Application, val category: Category) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CategoryProductViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CategoryProductViewModel(app, category) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}