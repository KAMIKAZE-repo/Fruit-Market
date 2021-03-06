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
import com.example.myapplication.utils.favoriteProducts
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

    private val _navigateToDestination = MutableLiveData<ProductCard>()
    val navigateToDestination : LiveData<ProductCard>
        get() = _navigateToDestination

    private val _navigateToCategory = MutableLiveData<Category>()
    val navigateToCategory : LiveData<Category>
        get() = _navigateToCategory

    init {
        Log.i("TAG", "ViewModel Created")
        _chips.value = chipsData
        _homeVisibility.value = HomeVisibility(
                View.GONE,
                View.VISIBLE,
                View.GONE
        )
        getData()

    }

    fun getData(){
        viewModelScope.launch {
            _getData()//TODO("Move it from here")
        }
    }

     private fun _getData(){
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
                        var products = repository.getAllProductByCategory(category.name).toMutableList()
                        products = products.subList(0, if(products.size > 10) 10 else products.size)//Show only 10 elements
                        val productsCards = mutableListOf<ProductCard>()
                        //forEach product we sent a request to get ProductInfo
                        products.forEachIndexed { _, product ->
                            val productCard = repository.getProductInfo(product.id)
                            productCard.offer = category.offer
                            productCard.productId = product.id.toLong()
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
                }
                _productHolderList.postValue(newData)
                _homeVisibility.postValue(
                    HomeVisibility(
                        View.VISIBLE,
                        View.GONE,
                        View.GONE
                    )
                )
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

            val newProductCard = ProductCard(
                newData[posHolder].productsCards[posProduct].imgUrl,
                newData[posHolder].productsCards[posProduct].name,
                newData[posHolder].productsCards[posProduct].price,
                true,
                newData[posHolder].category.offer,
                newData[posHolder].productsCards[posProduct].productId
            )

            val listProductCard = mutableListOf<ProductCard>()
            listProductCard.addAll(newData[posHolder].productsCards)
            listProductCard[posProduct] = newProductCard

            newData[posHolder] = ProductHolder(
                newData[posHolder].category,
                newData[posHolder].products,
                listProductCard
            )

            //For testing
            favoriteProducts.add(
                FavoriteProduct(
                    newProductCard
                ))
            //////////////////
            _productHolderList.value = newData
        }catch (e: Exception){
            Log.i("TAG", "$e")
        }
    }

    fun navigate(product: ProductCard){
        _navigateToDestination.value = product
    }

    fun navigationDone(){
        _navigateToDestination.value = null
    }


    fun navigateCategory(category: Category){
        _navigateToCategory.value = category
    }

    fun navigationCategoryDone(){
        _navigateToCategory.value = null
    }


}