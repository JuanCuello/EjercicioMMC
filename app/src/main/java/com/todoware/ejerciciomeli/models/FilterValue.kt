package com.todoware.ejerciciomeli.models

data class FilterValue(
    val id: String,
    val name: String,
    val path_from_root: List<PathFromRoot>
)