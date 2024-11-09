package com.uniploshop.repository

import com.uniploshop.network.RetrofitInstance
import com.uniploshop.network.model.ProductResponseModel

interface ProductRepository {
    suspend fun getAllProduct(): List<ProductResponseModel>
}

class ProductRepositoryImpl: ProductRepository {
    override suspend fun getAllProduct(): List<ProductResponseModel> {
        return RetrofitInstance.api.getProduct()
    }
}