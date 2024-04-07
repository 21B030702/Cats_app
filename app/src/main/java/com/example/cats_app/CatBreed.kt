package com.example.cats_app
import com.google.gson.annotations.SerializedName
data class CatBreed(
    val name: String,
    @SerializedName("min_weight") val minWeight: Double,
    @SerializedName("max_weight") val maxWeight: Double,
    @SerializedName("min_life_expectancy") val minLifeExpectancy: Int,
    @SerializedName("max_life_expectancy") val maxLifeExpectancy: Int,
    @SerializedName("playfulness") val playfulness: Int,
    @SerializedName("children_friendly") val childrenFriendly: Int,
)
