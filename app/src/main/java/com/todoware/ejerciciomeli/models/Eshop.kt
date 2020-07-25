package com.todoware.ejerciciomeli.models

data class Eshop(
    val eshop_experience: Int,
    val eshop_id: Int,
    val eshop_locations: List<Any>,
    val eshop_logo_url: String,
    val eshop_rubro: Any,
    val eshop_status_id: Int,
    val nick_name: String,
    val seller: Int,
    val site_id: String
)