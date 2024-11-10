package com.uniploshop.usecase

import com.uniploshop.model.Address
import com.uniploshop.model.Name
import com.uniploshop.model.UserUiModel
import com.uniploshop.network.model.UserResponseModel
import com.uniploshop.repository.UserRepository
import javax.inject.Inject

class AccountUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun getSessionUserDetail(): UserUiModel {
        return userRepository.getUserDetailProfile().toUiModel()
    }

    private fun UserResponseModel.toUiModel(): UserUiModel {
        return UserUiModel(
            name = Name(
                firstName = name.firstName,
                lastName = name.lastName
            ),
            email = email,
            phone = phone,
            address = Address(
                city = address.city,
                zipCode = address.zipCode
            )
        )
    }
}