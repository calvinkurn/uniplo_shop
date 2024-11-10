package com.uniploshop.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.uniploshop.R
import com.uniploshop.di.UniploShopApplication
import com.uniploshop.model.ProductUiModel
import com.uniploshop.ui.theme.UniploShopTheme
import com.uniploshop.ui.view.widget.HomeHeader
import com.uniploshop.ui.view.widget.ProductCard
import com.uniploshop.ui.view.widget.ProfileBottomSheet
import com.uniploshop.ui.viewmodel.HomeActivityViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val homeViewModel: HomeActivityViewModel by viewModels { viewModelFactory }

    private val result = mutableStateListOf<ProductUiModel>()
    private var isShowBottomSheet by mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initInjection()

        enableEdgeToEdge()
        setContent {
            UniploShopTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background
                ) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Row(
                            modifier = Modifier
                        ) {
                            HomeHeader(
                                title = stringResource(R.string.product_list_title),
                                onAccountClick = {
                                    openAccountBottomSheet()
                                }
                            )
                        }

                        LazyColumn(
                            modifier = Modifier.padding(4.dp)
                        ) {
                            items(result) {
                                ProductCard(it.title, it.price, it.rating, it.image)
                            }
                        }
                    }

                    if (isShowBottomSheet) {
                        ProfileBottomSheet(
                            userData = homeViewModel.userDetail
                        ) {
                            closeAccountBottomSheet()
                        }
                    }
                }
            }
        }

        observe()
        fetchProductList()
    }

    private fun openAccountBottomSheet() {
        isShowBottomSheet = true
        homeViewModel.fetchUserData()
    }

    private fun closeAccountBottomSheet() {
        isShowBottomSheet = false
    }

    private fun observe() {
        lifecycleScope.launch {
            homeViewModel.productList.collectLatest {
                result.addAll(it)
            }
        }
    }

    private fun fetchProductList() {
        homeViewModel.fetchAllProduct()
    }

    private fun initInjection() {
        (application as UniploShopApplication).appComponent.inject(this)
    }
}