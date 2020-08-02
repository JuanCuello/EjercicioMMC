package com.todoware.ejerciciomeli.service

import android.util.Log
import com.todoware.ejerciciomeli.models.SearchResponse
import com.todoware.ejerciciomeli.network.MercadolibreApi
import com.todoware.ejerciciomeli.network.RestClientBuilder
import retrofit2.Call

const val API_ADDRESS = "https://api.mercadolibre.com"
const val TAG = "NetworkLayer-repository"

class MercadoLibreService {

    var client: MercadolibreApi? = null

    fun searchData(query: String, offset: Int?): Call<SearchResponse> {

        var urlBase = overrideBaseUrl ?: API_ADDRESS
        client = client ?: RestClientBuilder.createService(
            MercadolibreApi::class.java,
            urlBase
        )
        Log.d(TAG, "Base url to be used $urlBase ")

        return client!!.search(query, offset)
    }

    // Required for MockWebServer
    companion object {
        var overrideBaseUrl: String? = null

    }
}

