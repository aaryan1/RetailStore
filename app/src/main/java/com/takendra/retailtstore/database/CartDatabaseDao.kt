package com.takendra.retailtstore.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface CartDatabaseDao {
    @Query("SELECT * from cart_table")
    fun getAll(): LiveData<List<ProductItem>>

    @Query("SELECT * from cart_table where prodId = :id")
    fun getById(id: Int) : ProductItem?

    @Insert(onConflict =OnConflictStrategy.IGNORE)
     fun insert(item:ProductItem)

    @Update
     fun update(item:ProductItem)

    @Delete
     fun delete(item:ProductItem)

    @Query("DELETE FROM cart_table")
     fun deleteAllTodos()

}