package com.uniploshop.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uniploshop.ui.DELAY_TIME
import com.uniploshop.usecase.LoginUseCase
import com.uniploshop.usecase.UserSessionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginActivityViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val userSessionUseCase: UserSessionUseCase
): ViewModel() {
    private var _isLoading = MutableStateFlow(false)
    val loadingState get() = _isLoading

    private var _loginErrorMsg = MutableStateFlow("")
    val loginErrorMsg get() = _loginErrorMsg

    private var _redirectState = MutableStateFlow(false)
    val redirectState get() = _redirectState

    fun userLogin(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _loginErrorMsg.tryEmit("")
            _isLoading.tryEmit(true)

            val (successLogin, errorMsg) = loginUseCase.login(username, password)
            // Note: UI consideration, to let user know there is some progress
            Thread.sleep(DELAY_TIME)

            _isLoading.tryEmit(false)
            _loginErrorMsg.tryEmit(errorMsg)
            _redirectState.tryEmit(successLogin)
        }
    }

    suspend fun checkUserSession() {
        _isLoading.tryEmit(true)

        val isLogin = userSessionUseCase.checkUserSession()
        // Note: UI consideration, to let user know there is some progress
        Thread.sleep(DELAY_TIME)

        _isLoading.tryEmit(false)
        if (isLogin) {
            _redirectState.tryEmit(true)
        }
    }
}