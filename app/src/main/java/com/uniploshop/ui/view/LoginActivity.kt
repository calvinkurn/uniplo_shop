package com.uniploshop.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.uniploshop.di.UniploShopApplication
import com.uniploshop.ui.theme.UniploShopTheme
import com.uniploshop.ui.viewmodel.LoginActivityViewModel
import com.uniploshop.ui.widget.LoginWidget
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val mainViewModel: LoginActivityViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initInjection()

        observe()

        enableEdgeToEdge()
        setContent {
            UniploShopTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        LoginWidget(
                            viewModel = mainViewModel,
                            onSubmit = { username, password ->
                                mainViewModel.userLogin(username, password)
                            }
                        )
                    }
                }
            }
        }
    }

    private fun observe() {
        lifecycleScope.launch {
            mainViewModel.loadingState.collectLatest {
                // show and hide loading widget
            }

            mainViewModel.loginErrorMsg.collectLatest {
                // show and hide error msg
            }
        }
    }

    private fun initInjection() {
        (application as UniploShopApplication).appComponent.inject(this)
    }
}