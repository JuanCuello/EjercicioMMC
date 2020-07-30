package com.todoware.ejerciciomeli.ui.resultsDashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.todoware.ejerciciomeli.models.Result
import com.todoware.ejerciciomeli.models.SearchResponse
import com.todoware.ejerciciomeli.repository.MercadoLibreRepository
import com.todoware.ejerciciomeli.utils.SearchParams

/**
 *  View model promoted to activity context please refer to this for the details
 *  https://developer.android.com/topic/libraries/architecture/viewmodel#sharing
 */

class ResultsViewModel() : ViewModel() {

    // Add the avility to trigger the repository to retrive the data
    var mlRepository: MercadoLibreRepository? = null
    fun setRepository(mercadoLibreRepository: MercadoLibreRepository) {
        mlRepository = mercadoLibreRepository
    }

    // Selected item to propagate between views
    val selected = MutableLiveData<Result>()
    fun select(item: Result) {
        selected.value = item
    }

    // Internal liveData that triggers the parametrize the search
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