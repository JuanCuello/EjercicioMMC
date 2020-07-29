package com.todoware.ejerciciomeli.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Use LinearLayoutManager to get the events.
 **/

abstract class PaginationListener
    (private val layoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        if (!isLoading()
            && visibleItemCount + firstVisibleItemPosition >= totalItemCount
            && firstVisibleItemPosition >= 0

        ) {
            loadMoreItems()
        }
    }

    abstract fun loadMoreItems()
    abstract fun isLoading() : Boolean

}