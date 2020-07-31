package com.todoware.ejerciciomeli.models

data class Filter(
    val id: String,
    val name: String,
    val type: String,
    val filterValues: List<FilterValue>
)