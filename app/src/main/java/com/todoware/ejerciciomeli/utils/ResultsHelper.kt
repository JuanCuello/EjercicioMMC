package com.todoware.ejerciciomeli.utils

import com.google.gson.annotations.SerializedName
import com.todoware.ejerciciomeli.R

// Class to allow us to modify the query params of the search, should be grow when we add advance search
data class SearchParams(val query: String = "", val offset: Int = 0)

/// Determines what kind of condition belong to the code, known conditions
enum class ItemCondition(val resourceId: Int) {
    @SerializedName("new")
    NEW(R.string.item_condition_new),

    @SerializedName("used")
    USED(R.string.item_condition_used),

    @SerializedName("refurbished")
    REFURBISHED(R.string.item_condition_refurvished),

    @SerializedName("unsupported")
    UNSUPPORTED(R.string.item_condition_unknown);

    companion object {

        private fun isKnownCondition(condition: String): Boolean {
            return values().any { it.serializedName == condition }
        }

        fun getConditionByName(name: String): Int {
            return if (isKnownCondition(name)) valueOf(name.toUpperCase()).resourceId else UNSUPPORTED.resourceId
        }
    }

}