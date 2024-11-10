package com.uniploshop.repository

import com.uniploshop.network.RetrofitInstance
import com.uniploshop.network.model.UserResponseModel
import javax.inject.Inject

interface UserRepository {
    suspend fun getAllUserProfile(): List<UserResponseModel>
    suspend fun getUserDetailProfile(): UserResponseModel
}

class UserRepositoryImpl @Inject constructor(
    private val authPreferenceRepository: AuthPreferenceRepository
): UserRepository {
    override suspend fun getAllUserProfile(): List<UserResponseModel> {
        return RetrofitInstance.api.getUser()
    }

    override suspend fun getUserDetailProfile(): UserResponseModel {
        val userId = authPreferenceRepository.getUserId()
        return RetrofitInstance.api.getUser(userId)
    }
}