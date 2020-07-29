package com.todoware.ejerciciomeli.ui.resultsDashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.todoware.ejerciciomeli.models.SearchResponse
import com.todoware.ejerciciomeli.repository.MercadoLibreRepository
import com.todoware.ejerciciomeli.utils.SearchParams

class ResultsViewModel() : ViewModel() {

    var mlRepository: MercadoLibreRepository? = null
    fun setRepository(mercadoLibreRepository: MercadoLibreRepository) {
        mlRepository = mercadoLibreRepository
    }

    // internal liveData that triggers the search on the repository
    private var searchFilters: MutableLiveData<SearchParams> = MutableLiveData<SearchParams>()
    var offset: Int = 0
    var lastSearch: String? = null

    // exposed livedata to be observed to get the results, this chains the repo livedata
    val searchResultsData: LiveData<SearchResponse> =
        Transformations.switchMap(searchFilters) { searchParams ->
            mlRepository?.searchItem(searchParams.query, searchParams.offset)
        }

    /**
     * Search in the api for this phrase
     * @query: String, the value to get
     **/
    fun searchQuery(query: String) {
        searchFilters.value = SearchParams(query, 0)
    }

    /**
     * Search in the api for this phrase and handle the offset of the query if match
     * @query: String, the value to get
     **/
    fun loadMore(query: String) {
        if (query != lastSearch) {
            offset = 0
        }
        setOffset(searchResultsData.value)
        searchFilters.value = SearchParams(query, offset)
    }

    private fun setOffset(newData: SearchResponse?) {
        newData?.let {
            if (offset == null || offset!! < it.paging.primary_results) {
                offset = offset + it.paging.limit // next Page
                lastSearch = it.query
            }
        }
    }

}