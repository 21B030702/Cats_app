package com.example.cats_app

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    const val API_KEY = "awnFXi548U1ZoQQ3ei16uQ==mcLKyD5Z3RDAbqDo"
    val api: CatsApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.api-ninjas.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatsApiService::class.java)
    }
}
