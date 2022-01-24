package es.santirivera.pruebamarvel.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessRecyclerViewScrollListener(
    layoutManager: LinearLayoutManager,
    private var visibleThreshold: Int
) :
    RecyclerView.OnScrollListener() {
    private var currentPage = 0
    private var previousTotalItemCount = 0
    private var loading = true


    // Sets the starting page index
    private val startingPageIndex = 0
    var mLayoutManager: RecyclerView.LayoutManager = layoutManager

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        var lastVisibleItemPosition = 0
        val totalItemCount: Int = mLayoutManager.itemCount
        lastVisibleItemPosition =
            (mLayoutManager as LinearLayoutManager).findLastVisibleItemPosition()

        if (totalItemCount < previousTotalItemCount) {
            currentPage = startingPageIndex
            previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                loading = true
            }
        }

        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
            currentPage++
            onLoadMore(currentPage, totalItemCount, recyclerView)
            loading = true
        }
    }

    fun loadFailed() {
        loading = false
    }

    abstract fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?)
}