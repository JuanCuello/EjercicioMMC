package com.todoware.ejerciciomeli.service

import com.todoware.ejerciciomeli.models.SearchResponse
import com.todoware.ejerciciomeli.network.MercadolibreApi
import com.todoware.ejerciciomeli.network.RestClientBuilder
import retrofit2.Call

const val API_ADDRESS = "https://api.mercadolibre.com"

class MercadoLibreService {
    var client: MercadolibreApi? = null

    fun searchData(query: String, offset: Int?) : Call<SearchResponse> {
        client = client ?: RestClientBuilder.createService(
            MercadolibreApi::class.java,
            overrideBaseUrl
                ?: API_ADDRESS
        )

        return client!!.search(query, offset)
    }

    // Required for MockWebServer
    companion object {
        var overrideBaseUrl: String? = null

    }
}

