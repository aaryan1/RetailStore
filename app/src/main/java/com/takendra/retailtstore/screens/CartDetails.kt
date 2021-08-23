package com.takendra.retailtstore.screens

import android.app.Application
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.takendra.retailtstore.DetailsActivity
import com.takendra.retailtstore.R
import com.takendra.retailtstore.database.CartViewModel
import com.takendra.retailtstore.database.CartViewModelFactory
import com.takendra.retailtstore.database.ProductItem
import com.takendra.retailtstore.model.Product

@Composable
fun CartDetails()
{
    var total=0

    val context = LocalContext.current

    val mCartViewModel: CartViewModel = viewModel(
        factory = CartViewModelFactory(context.applicationContext as Application)
    )


    val items=mCartViewModel.readAllData.observeAsState(listOf()).value

    items.forEach {
        total+=it.prodPrice*it.prodQuantity
    }


    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(modifier = Modifier.padding(start = 10.dp,end = 10.dp)) {
        Column() {
            Text("Cart items List ")
        }
            Column(modifier = Modifier.padding(start = 20.dp)) {
                Text("Cart Total $total")
            }
    }
        Spacer(modifier = Modifier.padding(bottom = 16.dp))
        productList(list = items, mCartViewModel = mCartViewModel)
        Spacer(modifier = Modifier.padding(top = 32.dp))

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun productList(
    list: List<ProductItem>,
    mCartViewModel: CartViewModel
) {

    LazyColumn() {
        items(
            items=list,
            itemContent = {
                productListItem(productItem = it,mCartViewModel=mCartViewModel )
            }
        )

    }
}
@Composable
fun productListItem(productItem: ProductItem, mCartViewModel: CartViewModel)
{
    val context = LocalContext.current

    Card(modifier = Modifier
        .padding(top = 16.dp)
        .fillMaxWidth(),
        elevation = 4.dp) {
        Row(modifier = Modifier
            .padding(16.dp)
            .clickable {
                val prod = Product(
                    productItem.prodId.toString(),
                    productItem.prodName,
                    productItem.prodCategory,
                    productItem.prodPrice,
                    getProductImageID(imageId = productItem.prodId.toInt())
                )
                context.startActivity(DetailsActivity.newIntent(context = context, prod))
            }

        ) {


            productImage(productItem.prodId.toInt())

            Spacer(modifier = Modifier.width(8.dp))

            Column(modifier = Modifier.padding(4.dp)) {
                Text(
                    text = productItem.prodName!!,
                    style = TextStyle(
                        color = Color.Blue,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )
                )

                Text(
                    text = "Price : "+productItem.prodPrice!!,
                    modifier = Modifier.padding(top = 8.dp)
                )

                Button(onClick = {
                    mCartViewModel.deleteProduct(productItem = productItem)

                },modifier = Modifier.padding(0.dp,10.dp,0.dp,0.dp))
                { Text("Delete")  }

            }

            Spacer(modifier = Modifier.width(8.dp))

            /**column ends here*/
        }/** row ends here*/
    }
}

@Composable
private fun productImage(imageId:Int):Int {

  var image=getProductImageID(imageId)

    Image(
        painter = painterResource(image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
    )
    return image
}
private fun getProductImageID(imageId:Int):Int
{
    var image=0
    when (imageId) {
        1 -> image=R.drawable.prod1
        2 -> image=R.drawable.prod2
        3 -> image=R.drawable.prod3
        4 -> image=R.drawable.prod4
        5 -> image=R.drawable.prod5
        6 -> image=R.drawable.prod6
    }
    return image
}
