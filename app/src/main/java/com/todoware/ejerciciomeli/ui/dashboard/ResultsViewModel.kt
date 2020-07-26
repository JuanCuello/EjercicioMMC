package com.todoware.ejerciciomeli.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.todoware.ejerciciomeli.models.Response
import com.todoware.ejerciciomeli.service.MercadoLibreService
import kotlinx.coroutines.Dispatchers

class ResultsViewModel : ViewModel() {

    private val mercadoLibreRepository = MercadoLibreService()

    val data: LiveData<Response?> = liveData(Dispatchers.IO) {
        val retrievedData = mercadoLibreRepository.searchData("macbook")
        var data = retrievedData.execute()
        emit(data.body())
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }

    val searchResponse: LiveData<Response?> = data
}