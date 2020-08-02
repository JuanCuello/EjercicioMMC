package com.todoware.ejerciciomeli.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.todoware.ejerciciomeli.models.SearchResponse
import com.todoware.ejerciciomeli.service.MercadoLibreService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "NetworkLayer-repository"

class MercadoLibreRepository {
    var client = MercadoLibreService()


    fun searchItem(query: String, offset: Int? = null): LiveData<SearchResponse> {

        val data = MutableLiveData<SearchResponse>()

        client.searchData(query, offset).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                searchResponse: Response<SearchResponse>
            ) {
                data.value = searchResponse.body()
            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                Log.e(TAG, "Network exception: ${t.message} with stack: ${t.cause}, ")
                data.value = null
            }
        })

        Log.d(TAG, "Starting network request ")
        return data
    }

}
