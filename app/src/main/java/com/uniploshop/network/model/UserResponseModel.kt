package com.uniploshop.network.model

import com.google.gson.annotations.SerializedName

data class UserResponseModel(
    @SerializedName("address") val address: Address,
    @SerializedName("id") val id: Int,
    @SerializedName("email") val email: String,
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,
    @SerializedName("name") val name: Name,
    @SerializedName("phone") val phone: String
)

data class Address(
    @SerializedName("geolocation") val geolocation: GeoLocation,
    @SerializedName("city") val city: String,
    @SerializedName("street") val street: String,
    @SerializedName("number") val number: Int,
    @SerializedName("zipcode") val zipCode: String,
)

data class GeoLocation(
    @SerializedName("lat") val lat: String,
    @SerializedName("long") val long: String
)

data class Name(
    @SerializedName("firstname") val firstName: String,
    @SerializedName("lastname") val lastName: String
)