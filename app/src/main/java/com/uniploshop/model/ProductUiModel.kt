package com.uniploshop.model

import com.google.gson.annotations.SerializedName
import com.uniploshop.network.model.Rating

data class ProductUiModel(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("price") var price: Double? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("category") var category: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("rating") var rating: Rating? = Rating()
)

data class Rating(
    @SerializedName("rate") var rate: Double? = null,
    @SerializedName("count") var count: Int? = null
)

val ProductUiModelSample get() = ProductUiModel(
    title = "Sample Product Ui Model Object"
)