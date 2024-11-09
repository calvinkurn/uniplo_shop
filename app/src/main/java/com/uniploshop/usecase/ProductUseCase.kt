package com.uniploshop.usecase

import com.uniploshop.model.ProductUiModel
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

    private fun ProductResponseModel.toProductUiModel(): ProductUiModel {
        return ProductUiModel(
            id = id,
            title = title,
            price = price,
            description = description,
            category = category,
            image = image,
            rating = Rating(
                rate = rating?.rate,
                count = rating?.count
            )
        )
    }
}