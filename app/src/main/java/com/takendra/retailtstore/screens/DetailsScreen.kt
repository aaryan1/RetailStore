
package com.takendra.retailtstore.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.takendra.retailtstore.CartFab
import com.takendra.retailtstore.R
import com.takendra.retailtstore.baselineHeight
import com.takendra.retailtstore.data.DataProvider
import com.takendra.retailtstore.model.Product


@Composable
fun DetailsScreen(product:Product, onNavIconPressed: () -> Unit = { }) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints(modifier = Modifier.weight(1f)) {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                ) {
                    DetailsHeader(
                        scrollState,
                        product,
                        this@BoxWithConstraints.maxHeight
                    )
                    DetailsContent(product, this@BoxWithConstraints.maxHeight)
                }
            }
            CartFab(
                extended = scrollState.value == 0,
                modifier = Modifier.align(Alignment.BottomEnd)
            )

        }
    }
}

@Composable
private fun DetailsHeader(
    scrollState: ScrollState,
    product: Product,
    containerHeight: Dp
) {
    val offset = (scrollState.value / 2)
    val offsetDp = with(LocalDensity.current) { offset.toDp() }

    Image(
        modifier = Modifier
            .heightIn(max = containerHeight / 2)
            .fillMaxWidth()
            .padding(top = offsetDp),
        painter = painterResource(id = product.productImageId),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Composable
private fun DetailsContent(product: Product, containerHeight: Dp) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))

        Name(product)

        DetailsProperty(stringResource(R.string.prod_name), product.prodName!!)

        DetailsProperty(stringResource(R.string.prod_price), product.prodPrice.toString())

        Spacer(Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}

@Composable
private fun Name(product: Product) {

    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Name(
            product = product,
            modifier = Modifier.baselineHeight(32.dp)
        )
    }
}

@Composable
private fun Name(product: Product, modifier: Modifier = Modifier) {
    Text(
        text = product.prodName!!,
        modifier = modifier,
        style = MaterialTheme.typography.h5,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun DetailsProperty(label: String, value: String, isLink: Boolean = false) {
    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
        Divider()
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = label,
                modifier = Modifier.baselineHeight(24.dp),
                style = MaterialTheme.typography.caption,
            )
        }
        val style = if (isLink) {
            MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.primary)
        } else {
            MaterialTheme.typography.body1
        }
        Text(
            text = value,
            modifier = Modifier.baselineHeight(24.dp),
            style = style
        )
    }
}

@Preview
@Composable
fun DetailsPreview() {
    val product = DataProvider.product
    DetailsScreen(product = product)
}
