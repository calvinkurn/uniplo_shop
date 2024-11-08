package com.uniploshop.repository

import android.content.Context
import android.content.SharedPreferences

interface AuthPreferenceRepository {
    fun saveAuthToken(token: String)
    suspend fun getAuthToken(): String?
}

class AuthPreferenceRepositoryImpl(
    context: Context
): AuthPreferenceRepository {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(AUTH_PREF, Context.MODE_PRIVATE)

    override suspend fun getAuthToken(): String? {
        return sharedPreferences.getString(AUTH_KEY, null)
    }

    override fun saveAuthToken(token: String) {
        sharedPreferences.edit().putString(AUTH_KEY, token).apply()
    }

    private companion object {
        const val AUTH_KEY = "auth_token"
        const val AUTH_PREF = "auth_pref"
    }
}