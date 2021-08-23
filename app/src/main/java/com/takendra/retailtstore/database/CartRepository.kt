package com.takendra.retailtstore.database

import androidx.lifecycle.LiveData


class CartRepository(private val cartDatabaseDao: CartDatabaseDao) {

    val readAllData : LiveData<List<ProductItem>> = cartDatabaseDao.getAll()

     fun addProduct(productItem: ProductItem) {
        cartDatabaseDao.insert(productItem)
    }

     fun updateProduct(productItem: ProductItem) {
        cartDatabaseDao.update(productItem)
    }

     fun deleteProduct(productItem: ProductItem) {
        cartDatabaseDao.delete(productItem)
    }

     fun deleteAllProducts() {
        cartDatabaseDao.deleteAllTodos()
    }

}