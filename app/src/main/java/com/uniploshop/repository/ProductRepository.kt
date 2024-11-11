package com.uniploshop.repository

import com.uniploshop.network.RetrofitInstance
import com.uniploshop.network.model.ProductResponseModel

interface ProductRepository {
    suspend fun getAllProduct(): List<ProductResponseModel>
    suspend fun getProductByCategory(category: String): List<ProductResponseModel>
    suspend fun getProduct(productId: Int): ProductResponseModel
    suspend fun getProductCategories(): List<String>
}

class ProductRepositoryImpl: ProductRepository {
    override suspend fun getAllProduct(): List<ProductResponseModel> {
        return RetrofitInstance.api.getProduct()
    }

    override suspend fun getProduct(productId: Int): ProductResponseModel {
        return RetrofitInstance.api.getProduct(productId)
    }

    override suspend fun getProductByCategory(category: String): List<ProductResponseModel> {
        return RetrofitInstance.api.getProductByCategory(category)
    }

    override suspend fun getProductCategories(): List<String> {
        return RetrofitInstance.api.getProductCategories()
    }
}