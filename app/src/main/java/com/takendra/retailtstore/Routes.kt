package com.takendra.retailtstore

sealed class Routes (val route:String){
    object Details:Routes("details")

}