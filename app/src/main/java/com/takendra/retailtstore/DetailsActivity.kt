
package com.takendra.retailtstore

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.takendra.retailtstore.model.Product
import com.takendra.retailtstore.screens.DetailsScreen
import com.takendra.retailtstore.ui.theme.RetailtStoreTheme


class DetailsActivity : AppCompatActivity() {

    private val product: Product by lazy {
        intent?.getSerializableExtra(PRODUCT_ID) as Product
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetailtStoreTheme {
                DetailsScreen(product)
            }
        }
    }

    companion object {
        private const val PRODUCT_ID = "product_id"
        fun newIntent(context: Context, product: Product) =
            Intent(context, DetailsActivity::class.java).apply {
                putExtra(PRODUCT_ID, product)
            }
    }
}
