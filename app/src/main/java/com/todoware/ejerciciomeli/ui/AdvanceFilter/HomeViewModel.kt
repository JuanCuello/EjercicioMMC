package com.todoware.ejerciciomeli.ui.AdvanceFilter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.todoware.ejerciciomeli.models.SearchResponse
import com.todoware.ejerciciomeli.repository.MercadoLibreRepository

class HomeViewModel() : ViewModel() {
    // Repository to observe
    fun setRepository(mercadoLibreRepository: MercadoLibreRepository) {
        mlRepository = mercadoLibreRepository
    }

    private var searchFilters: MutableLiveData<String> = MutableLiveData()
    var mlRepository: MercadoLibreRepository? = null

    // Livedata with the filters updated
    val searchResultsData: LiveData<SearchResponse> =
        Transformations.switchMap(searchFilters) { data ->
            mlRepository?.searchItem(data)
        }

    fun updateSearchQuery(query: String) {
        searchFilters.value = query
    }

}
