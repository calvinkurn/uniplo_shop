package com.uniploshop.usecase

import com.uniploshop.model.ProductUiModel
import com.uniploshop.model.toProductUiModel
import com.uniploshop.network.model.Rating
import com.uniploshop.network.model.ProductResponseModel
import com.uniploshop.repository.ProductRepository
import javax.inject.Inject

class ProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
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
}