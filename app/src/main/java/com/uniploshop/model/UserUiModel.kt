package com.uniploshop.model

data class UserUiModel(
    val address: Address = Address(),
    val email: String = "sample@gmail.com",
    val name: Name = Name(),
    val phone: String = "02-324 521 324"
)

data class Address(
    val city: String = "City of",
    val zipCode: String = "13029",
)

data class Name(
    val firstName: String = "Zuko",
    val lastName: String = "Albama"
) {
    fun getFullName(): String {
        return "$firstName $lastName"
    }
}