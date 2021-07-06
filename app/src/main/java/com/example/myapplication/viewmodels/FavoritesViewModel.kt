package com.example.myapplication.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.myapplication.database.ProductDataBase
import com.example.myapplication.model.FavoriteProduct
import com.example.myapplication.model.ProductCard
import com.example.myapplication.model.toDataBaseModel
import com.example.myapplication.repositroies.FoodDatabaseRepo
import com.example.myapplication.utils.favoriteProducts
import kotlinx.coroutines.launch

class FavoritesViewModel(app: Application): AndroidViewModel(app) {

    private val dataSource = ProductDataBase.getInstance(app)
    private val repository = FoodDatabaseRepo(dataSource)

    private val _listFavorites = MutableLiveData<List<FavoriteProduct>>()
    val listFavorite: LiveData<List<FavoriteProduct>>
        get() = _listFavorites

    init {
        _listFavorites.value = favoriteProducts
    }

    fun updateAmount(value: Int, pos: Int){
        val newData = mutableListOf<FavoriteProduct>()
        _listFavorites.value?.let { newData.addAll(it) }
        val newProduct = with(newData[pos]){
            FavoriteProduct(
                this.productCard,
                if(this.amount + value <0) 0 else this.amount + value
            )
        }
        newData[pos] = newProduct
        _listFavorites.value = newData
    }

    fun addToBuy(product: ProductCard, amount: Int){
        viewModelScope.launch {
            repository.addNewProductToBy(product.toDataBaseModel(amount))
        }
    }


    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FavoritesViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}