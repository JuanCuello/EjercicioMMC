package com.todoware.ejerciciomeli.models

data class Cancellations(
    val excluded: Excluded,
    val period: String,
    val rate: Int,
    val value: Int
)