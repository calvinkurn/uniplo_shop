package com.uniploshop.repository

import android.content.Context
import android.content.SharedPreferences

interface AuthPreferenceRepository {
    fun saveAuthToken(token: String, userId: Int)
    suspend fun getAuthToken(): String?
    suspend fun getUserId(): Int
    suspend fun checkUserSession(): SessionStatus
    suspend fun clearAuth()
}

// TODO: update security of token
// TODO: check session every accessing session & kick user when validity is expired
class AuthPreferenceRepositoryImpl(
    context: Context
): AuthPreferenceRepository {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(AUTH_PREF, Context.MODE_PRIVATE)

    // fakestoreapi provide login API but other API didn't accept token as identifier, user still use UserID as identifier
    override suspend fun getAuthToken(): String? {
        return sharedPreferences.getString(AUTH_KEY, null)
    }

    // due to user token is useless we can only use userid to access related user detail
    override suspend fun getUserId(): Int {
        return sharedPreferences.getInt(ID_KEY, -1)
    }

    override fun saveAuthToken(token: String, userId: Int) {
        sharedPreferences.edit().also {
            it.putString(AUTH_KEY, token).apply()
            it.putInt(ID_KEY, userId).apply()
            it.putLong(VALID_KEY, System.currentTimeMillis() + VALID_DURATION).apply()
        }
    }

    override suspend fun checkUserSession(): SessionStatus {
        val token = sharedPreferences.getString(AUTH_KEY, null)
        val validity = sharedPreferences.getLong(VALID_KEY, 0L)

        return if (!token.isNullOrEmpty() && validity > System.currentTimeMillis()) {
            SessionStatus.Valid
        } else {
            clearAuth()
            SessionStatus.Invalid
        }
    }

    override suspend fun clearAuth() {
        sharedPreferences.edit().also {
            it.putString(AUTH_KEY, "").apply()
            it.putLong(VALID_KEY, 0L).apply()
        }
    }

    private companion object {
        const val AUTH_KEY = "auth_token"
        const val VALID_KEY = "validity"
        const val ID_KEY = "user_id"
        const val AUTH_PREF = "auth_pref"
        const val VALID_DURATION = 3_600_000 // expire on 1 hour
    }
}

enum class SessionStatus{
    Valid,
    Invalid
}