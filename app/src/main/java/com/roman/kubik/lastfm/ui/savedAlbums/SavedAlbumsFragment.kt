package com.roman.kubik.lastfm.ui.savedAlbums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.roman.kubik.lastfm.R
import com.roman.kubik.lastfm.repository.model.Album
import com.roman.kubik.lastfm.ui.base.BaseFragment
import com.roman.kubik.lastfm.ui.utils.GridColumnDecorator
import kotlinx.android.synthetic.main.fragment_saved_albums.*
import javax.inject.Inject

class SavedAlbumsFragment: BaseFragment(), SavedAlbumsAdapterCallback {

    private val adapter = SavedAlbumsAdapter(this)

    @Inject
    lateinit var viewModel: SavedAlbumsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_saved_albums, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupViews()
    }

    private fun setupToolbar() {
        if (activity is AppCompatActivity) {
            val act = activity as AppCompatActivity
            act.setSupportActionBar(savedToolbar)
        }
    }

    private fun setupObservers() {
        viewModel.getSavedAlbums().observe(this, Observer {
            savedEmptyState?.visibility = if (it.isNullOrEmpty()) View.VISIBLE else View.GONE
            savedList?.scheduleLayoutAnimation()
            adapter.submitList(it)
        })
    }

    private fun setupViews() {
        val decorator = GridColumnDecorator(resources.getDimensionPixelSize(R.dimen.default_padding_tiny))
        savedList.layoutManager = GridLayoutManager(context, COLUMNS_COUNT, RecyclerView.VERTICAL, false)
        savedList.adapter = adapter
        savedList.addItemDecoration(decorator)
        savedSearchButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.searchFragment))
    }

    override fun onAlbumSelected(album: Album) {
        val direction = SavedAlbumsFragmentDirections.actionSavedAlbumsFragmentToAlbumDetailsFragment(album)
        findNavController().navigate(direction)
    }

companion object {
    const val COLUMNS_COUNT = 2
}
}