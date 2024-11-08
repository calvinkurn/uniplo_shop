package com.uniploshop.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.uniploshop.di.UniploShopApplication
import com.uniploshop.ui.theme.UniploShopTheme
import com.uniploshop.ui.viewmodel.LoginActivityViewModel
import com.uniploshop.ui.widget.LoginWidget
import kotlinx.coroutines.Dispatchers
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
            val isLoading = mainViewModel.loadingState.collectAsState().value

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

                        if (isLoading) {
                            Box(
                                modifier = Modifier.fillMaxSize().background(
                                    color = Color.Black.copy(alpha = 0.45f)
                                )
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.align(Alignment.Center),
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
            }
        }

        checkSession()
    }

    private fun checkSession() {
        lifecycleScope.launch(Dispatchers.IO) {
            mainViewModel.checkUserSession()
        }
    }

    private fun observe() {
        lifecycleScope.launch {
            mainViewModel.redirectState.collectLatest {
                if (it) {
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intent)

                    this@LoginActivity.finish()
                }
            }
        }
    }

    private fun initInjection() {
        (application as UniploShopApplication).appComponent.inject(this)
    }
}