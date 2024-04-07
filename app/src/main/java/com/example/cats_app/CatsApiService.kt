package com.example.cats_app

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CatsApiService {
    @GET("/v1/cats")
    suspend fun listCatBreeds(
        @Header("X-Api-Key") apiKey: String,
        @Query("min_life_expectancy") minLifeExpectancy: Int? = null,
        @Query("name") name: String? = null,
        @Query("min_weight") minWeight: Double? = null,
        @Query("max_weight") maxWeight: Double? = null,
        @Query("max_life_expectancy") maxLifeExpectancy: Int? = null,
        @Query("playfulness") playfulness: Int? = null,
        @Query("children_friendly") childrenFriendly: Int? = null
    ): Response<List<CatBreed>>
}
