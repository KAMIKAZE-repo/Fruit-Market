package com.example.myapplication.viewmodels

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.*
import com.example.myapplication.repositroies.FoodRepository
import com.example.myapplication.utils.chipsData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class HomeFragmentViewModel: ViewModel() {

    private val repository = FoodRepository()

    private val _chips = MutableLiveData<List<String>>()
    val chips: LiveData<List<String>>
        get() = _chips

    private val _productHolderList = MutableLiveData<List<ProductHolder>>()
    val productHolderList: LiveData<List<ProductHolder>>
        get() = _productHolderList

    private val _homeVisibility = MutableLiveData<HomeVisibility>()
    val homeVisibility: LiveData<HomeVisibility>
        get() = _homeVisibility

    private val _naviagteToDestination = MutableLiveData<Boolean>()
    val navigateToDestination : LiveData<Boolean>
        get() = _naviagteToDestination

    init {
        _chips.value = chipsData
        viewModelScope.launch {
            getData()
        }
    }

     fun getData(){
         _homeVisibility.postValue(
             HomeVisibility(
                 View.GONE,
                 View.VISIBLE,
                 View.GONE
             )
         )
        val newData = mutableListOf<ProductHolder>()
        viewModelScope.launch(Dispatchers.IO){
            try {
                //Request to get all Categories
                val categories = repository.getAllCategories()
                for (category in categories) {//n*n request forEach category we have n product!!! ==> Disaster
                    //request to get All productNetworkModel for each category
                    if(category.name.isNotEmpty()){
                        val products = repository.getAllProductByCategory(category.name).toMutableList()
                        val productsCards = mutableListOf<ProductCard>()
                        //forEach product we sent a request to get ProductInfo
                        products.forEachIndexed { _, product ->
                            val productCard = repository.getProductInfo(product.id)
                            productsCards.add(productCard)
                        }
                        newData.add(
                            ProductHolder(
                                category,
                                products,
                                productsCards
                            )
                        )
                    }
                    _productHolderList.postValue(newData)
                    _homeVisibility.postValue(
                        HomeVisibility(
                            View.VISIBLE,
                            View.GONE,
                            View.GONE
                        )
                    )
                }
            }catch (e: UnknownHostException){
                _homeVisibility.postValue(
                    HomeVisibility(
                        View.GONE,
                        View.GONE,
                        View.VISIBLE
                    )
                )
            }catch (e: Exception){
                Log.i("TAG", "$e")
            }
        }
    }

    //TODO("remove those blocking Thread Functions")
    /*fun getAllProducts(name: String):List<Product> = runBlocking {
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


    private suspend fun getCategoriesInitData(){
        val categories =  repository.getAllCategories()
        _categories.value = categories
    }*/

    fun addFavorites(posHolder: Int, posProduct: Int){
        try {
            val newData = mutableListOf<ProductHolder>()
            _productHolderList.value?.let { newData.addAll(it) }
            val newProductCard = with(newData[posHolder].productsCards[posProduct]){
                ProductCard(
                    this.imgUrl,
                    this.name,
                    this.price,
                    true
                )
            }
            newData[posHolder].productsCards[posProduct] = newProductCard
            _productHolderList.value = newData
        }catch (e: Exception){
            Log.i("TAG", "$e")
        }
    }

    fun navigate(){
        _naviagteToDestination.value = true
    }

    fun navigationDone(){
        _naviagteToDestination.value = false
    }
}