package com.example.iddeeafarislindov.data.network


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://odp.iddeea.gov.ba/"

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIyMzY1IiwibmJmIjoxNzUyNTE3MTI2LCJleHAiOjE3NTI2MDM1MjYsImlhdCI6MTc1MjUxNzEyNn0.yOc95vlM10U7E9mbuI2kWCwOrQB0GuyNLRYZaJTHQZly0v9Pv6bYj5Yl8YSkxXz_C_HGHGBtNAESCDzCEX-yZA")
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .build()
            chain.proceed(request)
        }
        .build()

    val odpApiService: ODPApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://odp.iddeea.gov.ba:8096/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ODPApiService::class.java)
    }
}
