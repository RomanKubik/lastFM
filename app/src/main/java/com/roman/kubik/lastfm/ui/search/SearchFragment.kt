package com.roman.kubik.lastfm.ui.search

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.roman.kubik.lastfm.R
import com.roman.kubik.lastfm.repository.model.Artist
import com.roman.kubik.lastfm.repository.model.Status
import com.roman.kubik.lastfm.ui.base.BaseFragment
import com.roman.kubik.lastfm.ui.utils.hideKeyboard
import kotlinx.android.synthetic.main.fragment_saved_albums.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_top_albums.*
import javax.inject.Inject


class SearchFragment : BaseFragment(), ArtistAdapterCallback {

    private val adapter = ArtistsAdapter(this)

    @Inject
    lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupRecyclerView()
        setupObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val menuItem = menu.findItem(R.id.menu_item_search)
        val searchView = if (menuItem?.actionView != null) menuItem.actionView as SearchView else null
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchViewModel.search(query)
                hideKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        menuItem?.expandActionView()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> findNavController().popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onArtistSelected(artist: Artist) {
        hideKeyboard()
        val direction =
            SearchFragmentDirections.actionSearchFragmentToAlbumsFragment(artist)
        findNavController().navigate(direction)
    }

    private fun setupToolbar() {
        if (activity is AppCompatActivity) {
            val act = activity as AppCompatActivity
            setHasOptionsMenu(true)
            act.setSupportActionBar(searchToolbar)
            act.supportActionBar?.title = getString(R.string.search_artists)
            act.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
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
            adapter.submitList(it)
        })
        searchViewModel.getNetworkState().observe(this, Observer {
            when (it.status) {
                Status.RUNNING -> {
                    searchProgress.visibility = View.VISIBLE
                    searchEmptyState?.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    searchProgress.visibility = View.GONE
                    searchEmptyState?.visibility = View.GONE
                }
                Status.FAILED -> {
                    searchProgress.visibility = View.GONE
                    searchEmptyState?.visibility = View.VISIBLE
                }
                Status.EMPTY -> {
                    searchProgress.visibility = View.GONE
                    searchEmptyState?.visibility = View.VISIBLE
                }
            }
        })
    }
}