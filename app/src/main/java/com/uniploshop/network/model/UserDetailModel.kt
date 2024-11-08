package com.uniploshop.network.model

data class UserDetailModel(
    val address: Address,
    val id: Int,
    val email: String,
    val username: String,
    val password: String,
    val name: Name,
    val phone: String
)

data class Address(
    val geolocation: GeoLocation,
    val city: String,
    val street: String,
    val number: Int,
    val zipCode: String,
)

data class GeoLocation(
    val lat: String,
    val long: String
)

data class Name(
    val firstName: String,
    val lastName: String
)