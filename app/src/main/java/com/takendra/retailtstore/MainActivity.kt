package com.takendra.retailtstore

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.runtime.Composable

import com.takendra.retailtstore.model.Product
import com.takendra.retailtstore.ui.theme.RetailtStoreTheme
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetailtStoreTheme {

                Surface(color = MaterialTheme.colors.background) {
                    RetailStoreApp {
                        startActivity(DetailsActivity.newIntent(this, it))
                    }
                }
            }
        }
    }
}


@Composable
fun RetailStoreApp(navigateToDetails: (Product) -> Unit) {
    Scaffold(
        content = {
            HomeContent(navigateToDetails = navigateToDetails)
        }
    )




}
