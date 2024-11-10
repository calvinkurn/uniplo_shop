package com.uniploshop.usecase

import com.uniploshop.model.CartProductUiModel
import com.uniploshop.model.ProductUiModel
import com.uniploshop.model.toProductUiModel
import com.uniploshop.repository.AuthPreferenceRepository
import com.uniploshop.repository.CartRepository
import com.uniploshop.repository.ProductRepository
import javax.inject.Inject

class CartUseCase @Inject constructor(
    private val authPreferenceRepository: AuthPreferenceRepository,
    private val cartRepository: CartRepository,
    private val productRepository: ProductRepository
) {
    suspend fun getUserCart(): List<CartProductUiModel> {
        val userId = authPreferenceRepository.getUserId()
        val cartData = cartRepository.getUserCart(userId)

        val productDetailList = mutableMapOf<Int, CartProductUiModel>()

        // TODO: improve using cache from data list
        cartData.forEach {
            it.products.forEach { product ->
                val productId = product.productId

                val detail = productRepository.getProduct(productId)
                val item = productDetailList.getOrDefault(productId, null)

                if (item == null) {
                    productDetailList[productId] = CartProductUiModel(
                        productUiModel = detail.toProductUiModel(),
                        qty = product.quantity
                    )
                }
            }
        }

        return productDetailList.values.toList()
    }
}