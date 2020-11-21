package com.android.marvelcharacters.mvp.view.adapters

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.marvelcharacters.mvp.presenter.MainPresenter

class RecyclerViewOnScrollListener(private val mainPresenter: MainPresenter, private val offset: Int, private val count: Int, private val total: Int) : RecyclerView.OnScrollListener() {

    private var searching = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
        val threshold = 2
        val isLastPage = offset + count >= total
        val isAtEndOfRecyclerView = visibleItemCount + firstVisibleItemPosition + threshold >= totalItemCount

        if (!isLastPage && isAtEndOfRecyclerView) {
            if (!searching) {
                searching = true
                mainPresenter.searchCharactersNextPage(offset + count)
            }
            Log.d("TAG", "Debe cargar la siguiente página: offset = ${offset + count}")
        } else {
            searching = false
        }
    }
}