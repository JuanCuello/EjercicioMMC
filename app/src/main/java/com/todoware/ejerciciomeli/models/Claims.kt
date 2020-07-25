package com.todoware.ejerciciomeli.models

data class Claims(
    val excluded: ExcludedX,
    val period: String,
    val rate: Double,
    val value: Int
)