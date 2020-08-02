package com.todoware.ejerciciomeli.utils

import android.os.Handler

object UiUtils {

    var workRunnable: Runnable? = null

    /**
     * Avoid multiple server calls while the user search
     * @param editable the TextView to listen
     * @param oldText the last term to skip inconsistency
     * @param onChange The function to call if triggered
     * @param handler The owner of the runnables
     **/

    fun editTextDebounce(
        searchText: String?,
        oldText: String,
        onChange: (param: String) -> Unit,
        handler: Handler
    ) {

        // conditions to skip the change if the user insert a short term
        if (searchText.isNullOrEmpty() || searchText == oldText || searchText.length < 2)
            return

        workRunnable?.let { handler.removeCallbacks(workRunnable!!) }
        workRunnable = Runnable {
            onChange("${searchText}");
        }
        handler.postDelayed(workRunnable!!, 600)
    }


}