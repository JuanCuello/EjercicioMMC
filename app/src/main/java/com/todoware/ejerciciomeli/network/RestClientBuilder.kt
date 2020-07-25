package com.todoware.ejerciciomeli.network

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.todoware.ejerciciomeli.models.Response
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object RestClientBuilder {
    const val API_ADDRESS = "https://api.mercadolibre.com"

    val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
    val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()

    var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(API_ADDRESS)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }

}