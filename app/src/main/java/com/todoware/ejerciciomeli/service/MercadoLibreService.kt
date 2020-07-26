package com.todoware.ejerciciomeli.service

import com.todoware.ejerciciomeli.network.MercadolibreApi
import com.todoware.ejerciciomeli.network.RestClientBuilder

const val API_ADDRESS = "https://api.mercadolibre.com"

class MercadoLibreService {
    var client = RestClientBuilder.createService(
        MercadolibreApi::class.java,
        overrideBaseUrl ?: API_ADDRESS
    )

    suspend fun searchData(query: String) = client.search(query)

    companion object {
        var overrideBaseUrl: String? = null
    }
}

