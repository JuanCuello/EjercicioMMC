package com.todoware.ejerciciomeli.service

import com.todoware.ejerciciomeli.network.MercadolibreApi
import com.todoware.ejerciciomeli.network.RestClientBuilder

const val API_ADDRESS = "https://api.mercadolibre.com"

class MercadoLibreService {
    var client = RestClientBuilder.createService(
        MercadolibreApi::class.java,
        overrideBaseUrl ?: API_ADDRESS
    )

    fun searchData(query: String, offset: Int? ) = client.search(query, offset)

    // Required for MockWebServer
    companion object {
        var overrideBaseUrl: String? = null
    }
}

