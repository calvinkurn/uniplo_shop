package com.uniploshop.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.uniploshop.di.UniploShopApplication
import com.uniploshop.ui.theme.UniploShopTheme
import com.uniploshop.ui.view.widget.CartProductCard
import com.uniploshop.ui.viewmodel.CartActivityViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val cartViewModel: CartActivityViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initInjection()
        getUserCart()

        enableEdgeToEdge()
        setContent {
            val cartDataList by cartViewModel.cartProductData.collectAsState()

            UniploShopTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background
                ) { innerPadding ->
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        items(cartDataList) {productDetail ->
                            CartProductCard(
                                qty = productDetail.qty,
                                title = productDetail.productUiModel.title,
                                image = productDetail.productUiModel.image,
                                price = productDetail.productUiModel.price
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getUserCart() {
        cartViewModel.getUserCart()
    }

    private fun initInjection() {
        (application as UniploShopApplication).appComponent.inject(this)
    }
}