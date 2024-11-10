package com.uniploshop.usecase

import com.uniploshop.repository.AuthPreferenceRepository
import com.uniploshop.repository.LoginRepository
import com.uniploshop.repository.SessionStatus
import com.uniploshop.repository.UserRepository
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

// TODO: improve security on passing username & password
class SessionUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val authPreferenceRepository: AuthPreferenceRepository,
    private val userRepository: UserRepository
) {
    suspend fun login(
        username: String,
        password: String,
    ): Pair<Boolean, String> = suspendCancellableCoroutine { continuation ->
        loginRepository.login(
            username,
            password,
            onSuccess = {
                // get userid
                var userId = -1

                runBlocking {
                    userRepository.getAllUserProfile()
                }.let { listOfUserData ->
                    run escape@ {
                        listOfUserData.forEach { userData ->
                            if (userData.username == username && userData.password == password) {
                                userId = userData.id
                                return@escape
                            }
                        }
                    }
                }

                authPreferenceRepository.saveAuthToken(it, userId)
                continuation.resume(Pair(true, ""))
            }, onError = {
                continuation.resume(Pair(false, it ?: ""))
            }
        )
    }

    suspend fun checkUserSession(): Boolean {
        val isLogin = authPreferenceRepository.checkUserSession()
        return isLogin == SessionStatus.Valid
    }

    fun logout() {
        authPreferenceRepository.clearAuth()
    }
}