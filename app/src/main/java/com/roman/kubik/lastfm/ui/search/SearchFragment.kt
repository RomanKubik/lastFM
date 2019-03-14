package com.roman.kubik.lastfm.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.roman.kubik.lastfm.R
import com.roman.kubik.lastfm.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject


class SearchFragment : BaseFragment() {

    private val adapter = ArtistsAdapter()

    @Inject
    lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        setupObservers()

        searchList.postDelayed({
            searchViewModel.search("link")
        }, 3000)
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        searchList.layoutManager = layoutManager

        val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        searchList.addItemDecoration(decoration)

        searchList.adapter = adapter
    }

    private fun setupObservers() {
        searchViewModel.getArtists().observe(this, Observer {
            adapter.addArtists(it)
        })
    }
}