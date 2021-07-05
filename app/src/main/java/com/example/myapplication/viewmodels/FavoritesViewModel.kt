package com.example.myapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.FavoriteProduct
import com.example.myapplication.utils.favoriteProducts

class FavoritesViewModel: ViewModel() {

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
}