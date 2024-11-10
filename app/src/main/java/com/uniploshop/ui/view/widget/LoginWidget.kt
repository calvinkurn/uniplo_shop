package com.uniploshop.ui.view.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.uniploshop.repository.AuthPreferenceRepositoryImpl
import com.uniploshop.repository.LoginRepositoryImpl
import com.uniploshop.repository.UserRepositoryImpl
import com.uniploshop.ui.viewmodel.LoginActivityViewModel
import com.uniploshop.usecase.SessionUseCase

@Composable
fun LoginWidget(
    viewModel: LoginActivityViewModel,
    onSubmit: (username: String, password: String) -> Unit = { _, _ -> }
) {
    // TODO: remove after development
    var username by remember {
        mutableStateOf("kevinryan")
    }

    // TODO: remove after development
    var password by remember {
        mutableStateOf("kev02937@")
    }

    val error by viewModel.loginErrorMsg.collectAsState()

    ElevatedCard(

    ) {
        Column(
            modifier = Modifier.padding(vertical = 6.dp, horizontal = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(12.dp),
                text = "Uniplo Store",
                fontSize = TextUnit(32f, TextUnitType.Sp)
            )

            TextField(
                modifier = Modifier.padding(vertical = 4.dp),
                value = username,
                label = {
                    Text(text = "Username")
                },
                onValueChange = {
                    username = it
                }
            )

            TextField(
                modifier = Modifier.padding(top = 14.dp),
                value = password,
                label = {
                    Text(text = "Password")
                },
                onValueChange = {
                    password = it
                }
            )

            if (error.isNotEmpty()) {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = error,
                    color = Color.Red,
                    fontSize = TextUnit(16f, TextUnitType.Sp)
                )
            }

            Button(
                modifier = Modifier.padding(vertical = 12.dp),
                onClick = {
                    onSubmit(username, password)
                }
            ) {
                Text(text = "Login")
            }
        }
    }
}

@Preview
@Composable
private fun LoginWidgetPrivate() {
    val context = LocalContext.current
    val authRepo = AuthPreferenceRepositoryImpl(context)
    LoginWidget(
        LoginActivityViewModel(
            SessionUseCase(
                LoginRepositoryImpl(),
                authRepo,
                UserRepositoryImpl(authRepo),
            )
        )
    )
}