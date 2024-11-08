package com.uniploshop.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uniploshop.usecase.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginActivityViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): ViewModel() {
    private var _isLoading = MutableStateFlow(false)
    val loadingState get() = _isLoading

    private var _loginErrorMsg = MutableStateFlow("")
    val loginErrorMsg get() = _loginErrorMsg

    fun userLogin(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loginErrorMsg.tryEmit("")
            _isLoading.tryEmit(true)
            val (isLoginSuccess, errorMsg) = loginUseCase.login(username, password)
            _isLoading.tryEmit(false)
            _loginErrorMsg.tryEmit(errorMsg)
        }
    }
}