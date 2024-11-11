package com.uniploshop.network.model

import com.google.gson.annotations.SerializedName

data class AddToCartRequestModel(
    @SerializedName("userId") val userId: Int,
    @SerializedName("date") val date: String,
    @SerializedName("products")val products: List<AddToCartProduct>
)

data class AddToCartProduct(
    @SerializedName("productId") val productId: Int,
    @SerializedName("quantity") val quantity: Int
)