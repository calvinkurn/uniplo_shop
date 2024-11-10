package com.uniploshop.ui.view.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.uniploshop.R

@Composable
fun CartProductCard(
    title: String? = "Title",
    qty: Int? = 10,
    price: Double? = 100.10,
    image: String? = "",
    onDeleteClick: () -> Unit = {},
    onIncClick: () -> Unit = {},
    onDecClick: () -> Unit = {},
) {
    ElevatedCard(
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp)
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
                    contentDescription = "Test Content Desc",
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
                Text("$ $price / pcs", color = Color.Red)
                Text("Quantity $qty", color = Color.Red)
                Text("Total = $${(qty?.toDouble() ?: 0.0) * (price ?: 0.0)} ")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    onDeleteClick()
                }
            ) {
                Text("Delete")
            }
            Button(
                onClick = {
                    onIncClick()
                }
            ) {
                Text("+")
            }
            Button(
                onClick = {
                    onDecClick()
                }
            ) {
                Text("-")
            }
        }
    }
}

@Composable
@Preview
private fun CartProductCardPreview() {
    CartProductCard()
}