package com.uniploshop.ui.view.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.uniploshop.R
import com.uniploshop.network.model.Rating

@Composable
fun ProductCard(
    title: String? = "Title",
    price: Double? = 10.0,
    rating: Rating? = Rating(rate = 5.0, count = 10),
    image: String? = "",
    onClick: () -> Unit = {}
) {
    ElevatedCard(
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp),
        onClick = {
            onClick()
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Column(
                Modifier.fillMaxWidth(0.3f)
            ) {
                Image(
                    modifier = Modifier.aspectRatio(1f),
                    painter = rememberAsyncImagePainter(
                        model = image,
                        placeholder = painterResource(id = R.drawable.ic_launcher_background)
                    ),
                    contentDescription = title,
                )
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(8.dp)
            ) {
                Text(
                    title ?: "-",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
                Text("$ $price", color = Color.Red)
                Row {
                    Text("${rating?.rate} ★", color = Color.Magenta)
                    HorizontalDivider(
                        modifier = Modifier
                            .width(20.dp)
                            .align(Alignment.CenterVertically)
                            .padding(horizontal = 4.dp),
                        thickness = 1.dp,
                        color = Color.Black,
                    )
                    Text("${rating?.count} Sold")
                }
            }
        }
    }
}

@Composable
@Preview
private fun ProductCardPreview() {
    ProductCard()
}