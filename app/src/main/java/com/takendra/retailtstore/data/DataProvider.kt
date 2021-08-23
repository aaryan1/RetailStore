package com.takendra.retailtstore.data
import com.takendra.retailtstore.R
import com.takendra.retailtstore.model.Product


object DataProvider {

    val product =
        Product(
            prodId  = "1",
            prodName = "Mircrowave oven",
            prodCategory = "Electronics",
            prodPrice = 1000,
            productImageId= R.drawable.prod1
        )

    val productList = listOf(
        product,
        Product(
            prodId  = "2",
            prodName = "Television",
            prodCategory = "Electronics",
            prodPrice = 1200,
            productImageId= R.drawable.prod2
        ),
        Product(
            prodId  = "3",
            prodName = "Vaccum cleaner",
            prodCategory = "Electronics",
            prodPrice = 1300,
            productImageId= R.drawable.prod3
        ),
        Product(
            prodId  = "4",
            prodName = "Table",
            prodCategory = "Furniture",
            prodPrice = 1400,
            productImageId= R.drawable.prod4
        ),
        Product(
            prodId  = "5",
            prodName = "Chair",
            prodCategory = "Furniture",
            prodPrice = 500,
            productImageId= R.drawable.prod5
        ),
        Product(
            prodId  = "6",
            prodName = "Almirah",
            prodCategory = "Furniture",
            prodPrice = 10000,
            productImageId= R.drawable.prod6
        )

    )
}
