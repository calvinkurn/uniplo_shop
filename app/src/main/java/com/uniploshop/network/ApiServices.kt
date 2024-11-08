package com.uniploshop.network

import com.uniploshop.network.model.LoginRequestModel
import com.uniploshop.network.model.LoginResponseModel
import com.uniploshop.network.model.UserDetailModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

// TODO: Update using suspend, Call<T> should be direct T
interface ApiServices {
    @POST("/auth/login")
    fun login(@Body loginData: LoginRequestModel): Call<LoginResponseModel>
    @GET("/users")
    fun getUser(): Call<List<UserDetailModel>>
}