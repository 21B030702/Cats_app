package com.example.cats_app

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CatsApiService {
    @GET("/v1/cats")
    fun listCatBreeds(
        @Header("X-Api-Key") apiKey: String,
        @Query("name") name: String? = null
    ): Deferred<Response<List<CatBreed>>>
}
