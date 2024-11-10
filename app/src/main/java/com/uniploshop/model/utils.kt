package com.uniploshop.model

import com.uniploshop.network.model.ProductResponseModel
import com.uniploshop.network.model.Rating

fun ProductResponseModel.toProductUiModel(): ProductUiModel {
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