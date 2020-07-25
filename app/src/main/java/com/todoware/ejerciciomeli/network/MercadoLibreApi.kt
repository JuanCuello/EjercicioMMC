package com.todoware.ejerciciomeli.network

import com.todoware.ejerciciomeli.models.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface MercadolibreApi {

    @GET("sites/MLA/search?")
    fun search(@Query("q") query: String?): Call<Response>

}
