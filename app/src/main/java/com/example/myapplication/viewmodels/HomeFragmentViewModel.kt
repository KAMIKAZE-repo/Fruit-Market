package com.example.myapplication.viewmodels

import android.util.Log
import android.view.View
import androidx.lifecycle.*
import com.example.myapplication.model.*
import com.example.myapplication.repositroies.FoodRepository
import com.example.myapplication.utils.chipsData
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import java.util.*

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

    init {
        _chips.value = chipsData
        viewModelScope.launch {
            Log.i("TAG", "Launched Init")
            getData()
        }
    }

    fun getData(){
        _homeVisibility.value = HomeVisibility(
            View.GONE,
            View.VISIBLE,
            View.GONE,
        )
        val newData = mutableListOf<ProductHolder>()
        viewModelScope.launch {
            try {
                //Request to get all Categories
                val categories = repository.getAllCategories()
                for (category in categories) {//n*n request forEach category we have n product!!! ==> Disaster
                    //request to get All productNetworkModel for each category
                    /*if(category.name.isNotEmpty()){
                        val products = repository.getAllProductByCategory(category.name).toMutableList()
                        val productsCards = mutableListOf<ProductCard>()
                        //forEach product we sent a request to get ProductInfo
                        products.forEachIndexed { _, product ->
                            val productCard = repository.getProductInfo(product.id)
                            productsCards.add(productCard)
                        }*/
                    newData.add(
                        ProductHolder(
                            category,
                            listOf(),
                            listOf()
                        )
                    )
                    _productHolderList.value = newData
                    _homeVisibility.value = HomeVisibility(
                        View.VISIBLE,
                        View.GONE,
                        View.GONE
                    )
                }

            }catch (e: UnknownHostException){
                Log.i("TAG", "Error: $e")
                _homeVisibility.value = HomeVisibility(
                    View.GONE,
                    View.GONE,
                    View.VISIBLE
                )
            }catch (e: Exception){
                Log.i("TAG", "Error: $e")
            }
        }
    }

    fun getProducts(category: Category, pos: Int){
        val newData = mutableListOf<ProductHolder>()
        _productHolderList.value?.let { newData.addAll(it) }
        viewModelScope.launch {
            val products = repository.getAllProductByCategory(category.name)
            Log.i("TAG", "GETTING PRODUCT List for category:${category.name} $products")
            newData[pos] = ProductHolder(
                newData[pos].category,
                products,
                List(products.size){
                    ProductCard(
                    "",
                    "placeHolder",
                    0.0
                )
                }
            )
            _productHolderList.value = newData
        }
    }

    fun getProduct(posProductHolder: Int, posProduct: Int){
        try {
            val newData = mutableListOf<ProductHolder>()
            _productHolderList.value?.let { newData.addAll(it) }

            val productInfoList = mutableListOf<ProductCard>()
            _productHolderList.value?.get(posProductHolder)
                ?.let { productInfoList.addAll(it.productsCards) }

            viewModelScope.launch {
                val product = newData[posProductHolder].products[posProduct]
                Log.i("TAG", "GETTING PRODUCT: $product")
                val productCard = repository.getProductInfo(product.id)
                productInfoList[posProduct] = productCard
                newData[posProductHolder] = ProductHolder(
                    newData[posProductHolder].category,
                    newData[posProductHolder].products,
                    productInfoList
                )
                _productHolderList.value = newData
            }
        }catch (e: Exception){
            Log.i("TAG", "$e")
        }
    }
}