package com.takendra.retailtstore.model

import java.io.Serializable

data class Product(
    val prodId:String?
    ,val prodName:String?
    ,val prodCategory:String?
    ,val prodPrice:Int
    ,val productImageId:Int): Serializable
