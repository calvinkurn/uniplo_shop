package com.uniploshop.ui.view.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.uniploshop.R

@Composable
fun HomeHeader(
    title: String = "Title",
    onCartClick: () -> Unit = {},
    onAccountClick: () -> Unit = {}
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(title, fontSize = TextUnit(20f, TextUnitType.Sp))

            Row {
                Icon(
                    Icons.Default.ShoppingCart,
                    contentDescription = stringResource(R.string.cart_icon_desc),
                    modifier = Modifier.padding(end = 8.dp).clickable {
                        onCartClick()
                    },
                )
                Icon(
                    Icons.Default.AccountCircle,
                    contentDescription = stringResource(R.string.account_icon_desc),
                    modifier = Modifier.clickable {
                        onAccountClick()
                    }
                )
            }
        }

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = Color.Black,
            thickness = 1.dp
        )
    }
}

@Preview
@Composable
private fun HomeHeaderPreview() {
    HomeHeader()
}