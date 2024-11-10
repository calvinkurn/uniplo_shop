package com.uniploshop.network.model

import com.google.gson.annotations.SerializedName

data class UserCartResponseModel(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("userId") var userId: Int? = null,
    @SerializedName("date") var date: String? = null,
    @SerializedName("products") var products: ArrayList<Products> = arrayListOf(),
    @SerializedName("__v") var v: Int? = null
)

data class Products(
    @SerializedName("productId") var productId: Int = 0,
    @SerializedName("quantity") var quantity: Int = 0
)