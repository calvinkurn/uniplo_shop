package com.uniploshop.usecase

import com.uniploshop.repository.AuthPreferenceRepository
import com.uniploshop.repository.SessionStatus
import javax.inject.Inject

class UserSessionUseCase @Inject constructor(
    private val authPreferenceRepository: AuthPreferenceRepository
) {
    suspend fun checkUserSession(): Boolean {
        val isLogin = authPreferenceRepository.checkUserSession()
        return isLogin == SessionStatus.Valid
    }

    suspend fun clearUserSession() {
        authPreferenceRepository.clearAuth()
    }
}