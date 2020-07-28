package com.todoware.ejerciciomeli.models

data class SearchResponse(
    val available_filters: List<AvailableFilter>,
    val available_sorts: List<AvailableSort>,
    val filters: List<Filter>,
    val paging: Paging,
    val query: String,
    val related_results: List<Any>,
    var results: List<Result>,
    val secondary_results: List<Any>,
    val site_id: String,
    val sort: Sort
)