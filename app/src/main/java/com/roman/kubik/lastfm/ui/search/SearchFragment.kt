package com.roman.kubik.lastfm.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
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
        val layoutManager = LinearLayoutManager(context)
        searchList.layoutManager = layoutManager
        searchList.adapter = adapter

        searchViewModel.getArtists().observe(this, Observer {
            adapter.addArtists(it)
        })

        searchList.postDelayed({
            searchViewModel.search("link")
        }, 10000)
    }
}