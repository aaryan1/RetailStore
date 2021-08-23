package com.takendra.retailtstore.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_table")
data class ProductItem(

    @PrimaryKey(autoGenerate = true)
    var prodId: Long = 0L,

    @ColumnInfo(name = "prod_name")
    val prodName: String,

    @ColumnInfo(name = "prod_quantity")
    var prodQuantity:Int,

    @ColumnInfo(name = "prod_category")
    var prodCategory: String ,

    @ColumnInfo(name = "prod_price")
    var prodPrice:Int

)