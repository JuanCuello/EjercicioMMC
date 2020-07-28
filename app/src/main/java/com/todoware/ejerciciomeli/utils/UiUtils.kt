package com.todoware.ejerciciomeli.utils

import android.os.Handler
import android.text.Editable

object UiUtils {

    var workRunnable: Runnable? = null

    fun editTextDebouncer(
        editable: Editable?,
        oldText: String,
        onChange: (param: String) -> Unit,
        handler: Handler
    ) {

        val searchText = editable?.toString()?.trim()

        // conditions to skip the change
        if (searchText.isNullOrEmpty() || searchText == oldText || searchText.length < 2)
            return

        workRunnable?.let { handler.removeCallbacks(workRunnable) }
        workRunnable = Runnable {
            onChange("${searchText}");
        }
        handler.postDelayed(workRunnable, 900)
    }

}