package com.todoware.ejerciciomeli.models

data class Installments(
    val amount: Double,
    val currency_id: String,
    val quantity: Int,
    val rate: Double
)