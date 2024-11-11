package com.uniploshop.ui.view.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uniploshop.ui.CATEGORY_ALL

@Composable
fun FilterWidget(
    filterItem: List<String> = listOf(),
    onFilterChanged: (newFilter: String) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(filterItem.firstOrNull() ?: CATEGORY_ALL) }

    LaunchedEffect(expanded) {
        onFilterChanged(selectedOption)
    }

    Column(
        modifier = Modifier.clickable {
            expanded = true
        }
    ) {
        Text("Category: $selectedOption", modifier = Modifier.padding(8.dp).clickable {
            expanded = true
        })

        HorizontalDivider(
            color = Color.Black,
            thickness = 1.dp
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            // Display each option as a menu item
            filterItem.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        selectedOption = option
                        expanded = false
                    },
                    text = { Text(option) }
                )
            }
        }
    }
}

@Composable
@Preview
private fun FilterWidgetPreview() {
    FilterWidget()
}