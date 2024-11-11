package com.uniploshop.usecase

import com.uniploshop.model.ProductUiModel
import com.uniploshop.model.toProductUiModel
import com.uniploshop.repository.AuthPreferenceRepository
import com.uniploshop.repository.ProductRepository
import javax.inject.Inject

class ProductUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val authPreferenceRepository: AuthPreferenceRepository
) {
    suspend fun getAllProduct(): List<ProductUiModel> {
        return productRepository.getAllProduct().map { it.toProductUiModel() }
    }

    suspend fun getProductByCategory(category: String): List<ProductUiModel> {
        return productRepository.getProductByCategory(category).map { it.toProductUiModel() }
    }

    suspend fun getProductCategories(): List<String> {
        return productRepository.getProductCategories()
    }

    suspend fun addProductToCart(productId: Int): Int? {
        return productRepository.addProductToCart(authPreferenceRepository.getUserId(), productId = productId)
    }
}