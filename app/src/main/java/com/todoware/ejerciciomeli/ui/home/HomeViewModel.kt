package com.todoware.ejerciciomeli.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.todoware.ejerciciomeli.models.SearchResponse
import com.todoware.ejerciciomeli.repository.MercadoLibreRepository

class HomeViewModel(
    private var mlRepository: MercadoLibreRepository
) : ViewModel() {

    private var searchFilters: MutableLiveData<String> = MutableLiveData()

    val searchResultsData: LiveData<SearchResponse> =
        Transformations.switchMap(searchFilters) { data ->
            mlRepository.searchItem(data)
        }

    fun updateSearchQuery(query: String) {
        searchFilters.value = query
    }

}
