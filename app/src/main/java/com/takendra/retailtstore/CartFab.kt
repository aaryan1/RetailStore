package com.takendra.retailtstore

import android.app.Application
import android.content.Intent
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.takendra.retailtstore.database.CartViewModel
import com.takendra.retailtstore.database.CartViewModelFactory

@Composable
fun CartFab(extended: Boolean, modifier: Modifier = Modifier) {
    val context= LocalContext.current
    val mCartViewModel: CartViewModel = viewModel(
        factory = CartViewModelFactory(context.applicationContext as Application)
    )
    val items=mCartViewModel.readAllData.observeAsState(listOf()).value
    FloatingActionButton(
        onClick = {
            context.startActivity(Intent(context,CartActivity::class.java))

                    },modifier = modifier
            .padding(16.dp)
            .padding()
            .height(48.dp)
            .widthIn(min = 48.dp),
        backgroundColor = Color.Blue,
        contentColor = Color.White
    ) {
        AnimatingFabContent(
            icon = {
                Icon(
                    imageVector = Icons.Outlined.ShoppingCart,
                    contentDescription = stringResource(R.string.cart)+"("+items.size+")"
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.cart)+"("+items.size+")"
                )
            },
            extended = extended

        )
    }
}

