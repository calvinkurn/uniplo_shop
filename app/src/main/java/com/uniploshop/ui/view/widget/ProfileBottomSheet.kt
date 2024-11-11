package com.uniploshop.ui.view.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uniploshop.R
import com.uniploshop.model.UserUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileBottomSheet(
    userData: StateFlow<UserUiModel>,
    onLogoutClick: () -> Unit = {},
    onDismiss: () -> Unit = {}
) {
    val bottomSheetState = rememberModalBottomSheetState()

    val data by userData.collectAsState()

    ModalBottomSheet(
        sheetState = bottomSheetState,
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.25f),
            ) {
                Text(stringResource(R.string.name_label_text))
                Text(stringResource(R.string.email_label_text))
                Text(stringResource(R.string.city_label_text))
                Text(stringResource(R.string.zip_code_label_text))
                Text(stringResource(R.string.phone_label_text))

            }

            Column {
                Text(data.name.getFullName())
                Text(data.email)
                Text(data.address.city)
                Text(data.address.zipCode)
                Text(data.phone)
            }
        }

        Button(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(0.5f),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error
            ),
            onClick = {
                onLogoutClick()
            }
        ) {
            Text(stringResource(R.string.button_label_logout))
        }
    }
}

@Preview
@Composable
private fun ProfileBottomSheetPreview() {
    ProfileBottomSheet(
        userData = MutableStateFlow(UserUiModel())
    )
}