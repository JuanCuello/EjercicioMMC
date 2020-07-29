package com.todoware.ejerciciomeli.ui.resultsDashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.todoware.ejerciciomeli.models.SearchResponse
import com.todoware.ejerciciomeli.repository.MercadoLibreRepository
import com.todoware.ejerciciomeli.utils.SearchParams

class ResultsViewModel(
) : ViewModel() {

    var mlRepository:MercadoLibreRepository?=  null
    fun setRepository( mercadoLibreRepository: MercadoLibreRepository){
        mlRepository = mercadoLibreRepository
    }

    private var searchFilters: MutableLiveData<SearchParams> = MutableLiveData<SearchParams>()
    var offset: Int? = null

    val searchResultsData: LiveData<SearchResponse> =
        Transformations.switchMap(searchFilters) { searchParams ->
            mlRepository?.searchItem(searchParams.query, searchParams.offset)
        }

    fun updateSearchQuery(query: String) {
        searchFilters.value = SearchParams(query, 0)
    }

    fun loadMore(query: String) {
        setOffset(searchResultsData.value)
        searchFilters.value = SearchParams(query, offset!!)
    }

    private fun setOffset(newData: SearchResponse?) {
        newData?.let {
            if (offset == null || offset!! < it.paging.primary_results)
                offset = it.paging.limit
        }
    }

}