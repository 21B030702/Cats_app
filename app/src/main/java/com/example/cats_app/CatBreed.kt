package com.example.cats_app
import com.google.gson.annotations.SerializedName
data class CatBreed(
    val name: String,
    val origin: String,
    @SerializedName("image_link") val imageLink: String,
    @SerializedName("family_friendly") val familyFriendly: Int,
    val shedding: Int,
)
