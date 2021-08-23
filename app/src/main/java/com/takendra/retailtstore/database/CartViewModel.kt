package com.takendra.retailtstore.database

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<ProductItem>>
    private val repository: CartRepository

    init {
        val cartDatabaseDao = CartDataBase.getInstance(application).cartDatabaseDao()
        repository = CartRepository(cartDatabaseDao)
        readAllData = repository.readAllData
    }

    fun addProduct(productItem: ProductItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProduct(productItem)
        }
    }

    fun updateProduct(productItem: ProductItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateProduct(productItem = productItem)
        }
    }

    fun deleteProduct(productItem: ProductItem) {
        viewModelScope.launch (Dispatchers.IO ){
            repository.deleteProduct(productItem = productItem)
        }
    }

    fun deleteAllProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllProducts()
        }
    }

}

class CartViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}