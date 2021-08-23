package com.takendra.retailtstore

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.takendra.retailtstore.screens.CartDetails
import com.takendra.retailtstore.ui.theme.RetailtStoreTheme

class CartActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetailtStoreTheme() {
                Surface(color = MaterialTheme.colors.background) {
                    ScreenMain()
                }
            }
        }
    }
}

@Composable
fun ScreenMain() {

    val navController = rememberNavController()

    /**
     * NavHost Builds a navGraph to handle navigation, set the start destination to Home and
     * provide the navController which will control the navigation.
     */
    NavHost(navController = navController,startDestination = Routes.Details.route) {


        // Route : CartDetails
        composable(Routes.Details.route) {
            CartDetails()
        }

    }

}