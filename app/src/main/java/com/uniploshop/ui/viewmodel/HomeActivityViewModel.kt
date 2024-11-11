package com.uniploshop.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uniploshop.model.ProductUiModel
import com.uniploshop.model.UserUiModel
import com.uniploshop.ui.CATEGORY_ALL
import com.uniploshop.ui.DELAY_TIME
import com.uniploshop.usecase.AccountUseCase
import com.uniploshop.usecase.SessionUseCase
import com.uniploshop.usecase.ProductUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeActivityViewModel @Inject constructor(
    private val productUseCase: ProductUseCase,
    private val accountUseCase: AccountUseCase,
    private val loginUseCase: SessionUseCase
): ViewModel() {

    private val _productList = MutableStateFlow<List<ProductUiModel>>(listOf())
    val productList get() = _productList

    private val _userDetail = MutableStateFlow(UserUiModel())
    val userDetail get() = _userDetail

    private val _productCategories = MutableStateFlow<List<String>>(emptyList())
    val productCategories get() = _productCategories

    private var _isSuccessAdded = MutableStateFlow(false)
    val isSuccessAdded get() = _isSuccessAdded

    fun fetchAllProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            productUseCase.getAllProduct().also {
                _productList.tryEmit(it)
            }
        }
    }

    fun fetchProductByCategory(newCategory: String) {
        viewModelScope.launch(Dispatchers.IO) {
            productUseCase.getProductByCategory(newCategory).also {
                _productList.tryEmit(it)
            }
        }
    }

    fun fetchProductCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            productUseCase.getProductCategories().also {
                val newList = listOf(CATEGORY_ALL) + it

                _productCategories.emit(newList)
            }
        }
    }

    fun fetchUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            accountUseCase.getSessionUserDetail().let {
                _userDetail.tryEmit(it)
            }
        }
    }

    fun userLogout() {
        loginUseCase.logout()
    }

    fun atcProduct(productId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            productUseCase.addProductToCart(productId)?.let {
                if (it > -1) {
                    _isSuccessAdded.value = true

                    Thread.sleep(DELAY_TIME)

                    _isSuccessAdded.value = false
                }
            }
        }
    }
}