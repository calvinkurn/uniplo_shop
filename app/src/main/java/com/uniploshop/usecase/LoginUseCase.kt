package com.uniploshop.usecase

import com.uniploshop.repository.AuthPreferenceRepository
import com.uniploshop.repository.LoginRepository
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val authPreferenceRepository: AuthPreferenceRepository
) {
    suspend fun login(
        username: String,
        password: String,
    ): Pair<Boolean, String> = suspendCancellableCoroutine { continuation ->
        loginRepository.login(
            username,
            password,
            onSuccess = {
                authPreferenceRepository.saveAuthToken(it)
                continuation.resume(Pair(true, ""))
            }, onError = {
                continuation.resume(Pair(false, it ?: ""))
            }
        )
    }
}