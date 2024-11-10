package com.uniploshop.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uniploshop.model.CartProductUiModel
import com.uniploshop.usecase.CartUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartActivityViewModel @Inject constructor(
    private val cartUseCase: CartUseCase
): ViewModel() {
    private val _cartProductData = MutableStateFlow<List<CartProductUiModel>>(listOf())
    val cartProductData get() = _cartProductData

    fun getUserCart() {
        viewModelScope.launch(Dispatchers.IO) {
            _cartProductData.tryEmit(cartUseCase.getUserCart())
        }
    }
}