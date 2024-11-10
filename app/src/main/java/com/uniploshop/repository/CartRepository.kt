package com.uniploshop.repository

import com.uniploshop.network.RetrofitInstance
import com.uniploshop.network.model.UserCartResponseModel

interface CartRepository {
    suspend fun getUserCart(userId: Int): List<UserCartResponseModel>
}

class CartRepositoryImpl: CartRepository {
    override suspend fun getUserCart(userId: Int): List<UserCartResponseModel> {
        return RetrofitInstance.api.getUserCart(userId)
    }
}