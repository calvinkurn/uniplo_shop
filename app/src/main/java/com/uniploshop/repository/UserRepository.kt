package com.uniploshop.repository

interface UserRepository {
    suspend fun getUserProfile()
}

class UserRepositoryImpl(): UserRepository {
    override suspend fun getUserProfile() {

    }
}