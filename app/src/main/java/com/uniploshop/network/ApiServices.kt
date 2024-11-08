package com.uniploshop.network

import com.uniploshop.network.model.LoginBodyModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {
    @POST("/auth/login")
    fun userLogin(@Body loginData: LoginBodyModel): Call<String>
}