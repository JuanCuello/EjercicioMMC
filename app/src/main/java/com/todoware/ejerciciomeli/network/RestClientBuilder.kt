package com.todoware.ejerciciomeli.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RestClientBuilder {

    val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
    val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()

    var retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)

    fun <T> createService(serviceClass: Class<T>, base_url: String): T {
        return retrofit.baseUrl(base_url)
            .build()
            .create(serviceClass)
    }

}