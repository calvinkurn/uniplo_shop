package com.uniploshop.network.model

data class LoginResponseModel (
    val token: String? = null,
    val errorMsg: String = ""
)