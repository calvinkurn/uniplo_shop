package com.uniploshop.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.uniploshop.di.UniploShopApplication
import com.uniploshop.model.ProductUiModel
import com.uniploshop.model.ProductUiModelSample
import com.uniploshop.ui.theme.UniploShopTheme
import com.uniploshop.ui.view.widget.ProductCard
import com.uniploshop.ui.viewmodel.HomeActivityViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val homeViewModel: HomeActivityViewModel by viewModels { viewModelFactory }

    private val result = mutableStateListOf<ProductUiModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initInjection()

        enableEdgeToEdge()
        setContent {
            GreetingPreview(result)
        }

        observe()
        fetchProductList()
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

@Preview(showBackground = true)
@Composable
private fun GreetingPreview(data: List<ProductUiModel> = listOf(ProductUiModelSample)) {
    UniploShopTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column (
                modifier = Modifier.padding(innerPadding)
            ) {
                Row {
                    Text(text = "Title")
                }
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Red,
                    thickness = 2.dp
                )
                LazyColumn(
                    modifier = Modifier.padding(4.dp)
                ) {
                    items(data) {
                        ProductCard(it.title, it.price, it.rating, it.image)
                    }
                }
            }
        }
    }
}