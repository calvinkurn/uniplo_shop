package com.uniploshop.network

import com.uniploshop.network.model.LoginRequestModel
import com.uniploshop.network.model.LoginResponseModel
import com.uniploshop.network.model.ProductResponseModel
import com.uniploshop.network.model.UserResponseModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

// TODO: Update using suspend, Call<T> should be direct T
interface ApiServices {
    @POST("/auth/login")
    fun login(@Body loginData: LoginRequestModel): Call<LoginResponseModel>
    @GET("/products")
    suspend fun getProduct(): List<ProductResponseModel>
    @GET("/users")
    suspend fun getUser(): List<UserResponseModel>
    @GET("/users/{id}")
    suspend fun getUser(@Path("id") id: Int): UserResponseModel
}