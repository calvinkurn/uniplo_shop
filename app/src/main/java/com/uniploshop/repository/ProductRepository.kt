package com.uniploshop.repository

import com.uniploshop.network.RetrofitInstance
import com.uniploshop.network.model.ProductResponseModel

interface ProductRepository {
    suspend fun getAllProduct(): List<ProductResponseModel>
    suspend fun getProduct(productId: Int): ProductResponseModel
}

class ProductRepositoryImpl: ProductRepository {
    override suspend fun getAllProduct(): List<ProductResponseModel> {
        return RetrofitInstance.api.getProduct()
    }

    override suspend fun getProduct(productId: Int): ProductResponseModel {
        return RetrofitInstance.api.getProduct(productId)
    }
}