package com.uniploshop.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uniploshop.model.ProductUiModel
import com.uniploshop.model.UserUiModel
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

    private val _userDetail = MutableStateFlow<UserUiModel>(UserUiModel())
    val userDetail = _userDetail

    fun fetchAllProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            productUseCase.getAllProduct().also {
                _productList.tryEmit(it)
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
}