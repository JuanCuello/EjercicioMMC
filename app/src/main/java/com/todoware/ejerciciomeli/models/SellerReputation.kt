package com.todoware.ejerciciomeli.models

data class SellerReputation(
    val level_id: String,
    val power_seller_status: String,
    val protection_end_date: String,
    val real_level: String,
    val transactions: Transactions
)