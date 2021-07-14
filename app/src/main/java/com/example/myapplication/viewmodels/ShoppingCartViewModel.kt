package com.example.myapplication.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.myapplication.database.ProductDataBase
import com.example.myapplication.database.ProductEntity
import com.example.myapplication.repositroies.FoodDatabaseRepo
import com.example.myapplication.repositroies.FoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShoppingCartViewModel(app: Application): AndroidViewModel(app) {

    private val dataSource = ProductDataBase.getInstance(app)
    private val repository = FoodDatabaseRepo(dataSource)
    private val networkRepo = FoodRepository()

    val productList = repository.productsToBuy

    private val _totalPrice = MutableLiveData<Double>()
    val totalPrice: LiveData<Double>
        get() = _totalPrice


    fun updateAmount(value: Int, product: ProductEntity){
        //product.amount += value
        val newProduct = ProductEntity(
            product.productId,
            product.name,
            product.price,
            product.offer,
            product.imgUrl,
            product.amount + value,
            product.productNetworkId
        )
        viewModelScope.launch {
            repository.updateProductAmount(newProduct)
        }
    }

    fun deleteProduct(product: ProductEntity){
        viewModelScope.launch {
            repository.deleteProductOrder(product)
        }
    }

    private fun placeOrder(orderId: Long, product: ProductEntity){
        viewModelScope.launch {
            try {
                networkRepo.addNewProductToOrders(orderId, product)
            }catch (e: Exception){
                Log.i("TAG", "$e")
            }
        }
    }

    fun placeAllOrders(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                try {
                    val orderDetails = networkRepo.createNewOrder(2)
                    Log.i("TAG", "$orderDetails")
                    for(product in productList.value!!)
                        placeOrder(orderDetails.orderId, product)
                    dataSource.productDao.deleteAllProducts()
                }catch (e: Exception){
                    Log.i("TAG", "$e")
                }
            }
        }
    }

    fun updateTotalPrice(){
        var total = 0.0
        for(product in productList.value!!){
            total += product.price
        }
        _totalPrice.value = total
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ShoppingCartViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ShoppingCartViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}