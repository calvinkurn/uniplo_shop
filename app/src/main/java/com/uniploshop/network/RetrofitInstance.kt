package com.uniploshop.network

import com.google.gson.GsonBuilder
import com.uniploshop.network.model.utils.LoginModelDeserialize
import com.uniploshop.network.model.LoginResponseModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://fakestoreapi.com/"

    val api: ApiServices by lazy {
        val gson = GsonBuilder().registerTypeAdapter(LoginResponseModel::class.java, LoginModelDeserialize()).create()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServices::class.java)
    }
}