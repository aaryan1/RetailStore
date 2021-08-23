package com.takendra.retailtstore.screens

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.takendra.retailtstore.CartFab
import com.takendra.retailtstore.data.DataProvider
import com.takendra.retailtstore.database.CartViewModel
import com.takendra.retailtstore.database.CartViewModelFactory
import com.takendra.retailtstore.database.ProductItem
import com.takendra.retailtstore.model.Product


@Composable
fun productListItem(product: Product, navigateToDetail: (Product) -> Unit,) {

    val context = LocalContext.current

    val mCartViewModel: CartViewModel = viewModel(
        factory = CartViewModelFactory(context.applicationContext as Application)
    )

    Card(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 4.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.Black,
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
    ) {
        Row(Modifier.clickable { navigateToDetail(product) }) {
            productImage(product)
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = product.prodName!!, style = typography.h6,color=Color.White)
                Text(text = product.prodCategory!!, style = typography.caption,color=Color.White)
                Text(text = "Price : "+product.prodPrice!!.toString(), style = typography.caption,color=Color.White)

                Button(onClick = {

                    Toast.makeText(context,""+product.prodName, Toast.LENGTH_LONG).show()
                    mCartViewModel.addProduct(ProductItem(product.prodId!!.toLong(), product.prodName!!,1, product.prodCategory!!,product.prodPrice))

                },colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),modifier = Modifier.padding(0.dp,10.dp,0.dp,0.dp))
                { Text("Add to cart") }
            }


        }
    }


}

@Composable
private fun productImage(product: Product) {
    Image(
        painter = painterResource(id = product.productImageId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
    )
}

@Preview
@Composable
fun PreviewCartItem() {
    val product = DataProvider.product
    productListItem(product = product, navigateToDetail = {})
}
