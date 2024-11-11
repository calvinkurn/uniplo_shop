package com.uniploshop.repository

import com.uniploshop.network.RetrofitInstance
import com.uniploshop.network.model.AddToCartProduct
import com.uniploshop.network.model.AddToCartRequestModel
import com.uniploshop.network.model.ProductResponseModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

interface ProductRepository {
    suspend fun getAllProduct(): List<ProductResponseModel>
    suspend fun getProductByCategory(category: String): List<ProductResponseModel>
    suspend fun getProduct(productId: Int): ProductResponseModel
    suspend fun getProductCategories(): List<String>
    suspend fun addProductToCart(userId: Int, productId: Int): Int?
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

    override suspend fun addProductToCart(userId: Int, productId: Int): Int? {
        val date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        return RetrofitInstance.api.addProductToCart(
            AddToCartRequestModel(
                userId = userId,
                products = listOf(AddToCartProduct(productId = productId, quantity = 1)),
                date = date,
            )
        ).id
    }
}