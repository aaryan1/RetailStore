package com.takendra.retailtstore

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.takendra.retailtstore.data.DataProvider
import com.takendra.retailtstore.model.Product
import com.takendra.retailtstore.screens.productListItem


@Composable
fun HomeContent(navigateToDetails: (Product) -> Unit) {
    val scrollState = rememberScrollState()

    val products = remember { DataProvider.productList }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = products,
            itemContent = {
                productListItem(product = it, navigateToDetails)
            }
        )
    }

    BoxWithConstraints(modifier = Modifier.fillMaxWidth().fillMaxHeight())
    {
        CartFab(
            extended = scrollState.value == 0,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}
