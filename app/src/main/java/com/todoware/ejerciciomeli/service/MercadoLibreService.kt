package com.todoware.ejerciciomeli.service

import com.todoware.ejerciciomeli.network.MercadolibreApi
import com.todoware.ejerciciomeli.network.RestClientBuilder

class MercadoLibreService {
    var client = RestClientBuilder.createService(
        MercadolibreApi::class.java
    )

    suspend fun searchData(query: String) = client.search(query)
}