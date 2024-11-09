package com.uniploshop.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uniploshop.model.ProductUiModel
import com.uniploshop.usecase.ProductUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeActivityViewModel @Inject constructor(
    private val productUseCase: ProductUseCase
): ViewModel() {

    private val _productList = MutableStateFlow<List<ProductUiModel>>(listOf())
    val productList get() = _productList

    fun fetchAllProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            productUseCase.getAllProduct().also {
                _productList.tryEmit(it)
            }
        }
    }
}