package com.todoware.ejerciciomeli.utils

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Use LinearLayoutManager to get the events.
 **/

const val TAG = "PaginationListener"

abstract class PaginationListener
    (private val layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading()
            && totalItemCount > 0
            && visibleItemCount + firstVisibleItemPosition >= totalItemCount
            && firstVisibleItemPosition > 0

        ) {
            Log.d(TAG, "Pagination triggered.")
            loadMoreItems()
        }
    }

    abstract fun loadMoreItems()
    abstract fun isLoading(): Boolean

}