package com.uniploshop.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
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
import com.uniploshop.ui.CATEGORY_ALL
import com.uniploshop.ui.theme.UniploShopTheme
import com.uniploshop.ui.view.widget.FilterWidget
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

    private val productList = mutableStateListOf<ProductUiModel>()
    private val categories = mutableStateListOf<String>()
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
                        HomeHeader(
                            title = stringResource(R.string.product_list_title),
                            onAccountClick = {
                                openAccountBottomSheet()
                            },
                            onCartClick = {
                                navigateToCartPage()
                            }
                        )

                        FilterWidget(categories) {
                            filterHandler(it)
                        }

                        LazyColumn(
                            modifier = Modifier.padding(4.dp)
                        ) {
                            items(productList) {
                                ProductCard(it.title, it.price, it.rating, it.image)
                            }
                        }
                    }

                    if (isShowBottomSheet) {
                        ProfileBottomSheet(
                            userData = homeViewModel.userDetail,
                            onLogoutClick = {
                                userLogout()
                            },
                            onDismiss = {
                                closeAccountBottomSheet()
                            }
                        )
                    }
                }
            }
        }

        observe()
        fetchCategories()
    }

    private fun filterHandler(newCategory: String) {
        if (newCategory == CATEGORY_ALL) {
            homeViewModel.fetchAllProduct()
        } else {
            homeViewModel.fetchProductByCategory(newCategory)
        }
    }

    private fun userLogout() {
        homeViewModel.userLogout()
        val intent = Intent(this@HomeActivity, LoginActivity::class.java)
        startActivity(intent)

        this@HomeActivity.finish()
    }

    private fun navigateToCartPage() {
        val intent = Intent(this@HomeActivity, CartActivity::class.java)
        startActivity(intent)
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
                productList.clear()
                productList.addAll(it)
            }
        }

        lifecycleScope.launch {
            homeViewModel.productCategories.collectLatest {
                categories.clear()
                categories.addAll(it)
            }
        }
    }

    private fun fetchCategories() {
        homeViewModel.fetchProductCategories()
    }

    private fun initInjection() {
        (application as UniploShopApplication).appComponent.inject(this)
    }
}