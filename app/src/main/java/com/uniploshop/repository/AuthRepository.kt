package com.uniploshop.repository

import com.uniploshop.network.RetrofitInstance
import com.uniploshop.network.model.LoginRequestModel
import com.uniploshop.network.model.LoginResponseModel
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
        val call = RetrofitInstance.api.login(
            LoginRequestModel(username, password)
        )

        call.enqueue(object : Callback<LoginResponseModel> {
            override fun onResponse(call: Call<LoginResponseModel>, response: Response<LoginResponseModel>) {
                if (response.code() == 200) {
                    onSuccess(response.body()?.token ?: "")
                } else {
                    onError(response.errorBody()?.string())
                }
            }

            override fun onFailure(call: Call<LoginResponseModel>, t: Throwable) {
                onError(t.message)
            }
        })
    }

    override fun logout() {
        TODO("Not yet implemented")
    }
}