package com.uniploshop.repository

import com.uniploshop.network.RetrofitInstance
import com.uniploshop.network.model.LoginBodyModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface LoginRepository {
    fun login(
        username: String,
        password: String,
        onSuccess: (token: String) -> Unit,
        onError: (errorMsg: String?) -> Unit
    )
    fun logout()
}

class LoginRepositoryImpl : LoginRepository {
    override fun login(
        username: String,
        password: String,
        onSuccess: (token: String) -> Unit,
        onError: (errorMsg: String?) -> Unit
    ) {
        val call = RetrofitInstance.api.userLogin(
            LoginBodyModel(username, password)
        )
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.code() == 200) {
                    onSuccess(response.message())
                } else {
                    onError(response.errorBody()?.string())
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                onError(t.message)
            }
        })
    }

    override fun logout() {
        TODO("Not yet implemented")
    }
}