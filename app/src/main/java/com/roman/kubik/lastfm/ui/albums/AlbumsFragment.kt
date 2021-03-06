package com.roman.kubik.lastfm.ui.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.roman.kubik.lastfm.R
import com.roman.kubik.lastfm.repository.model.Album
import com.roman.kubik.lastfm.repository.model.Status
import com.roman.kubik.lastfm.ui.base.BaseFragment
import com.roman.kubik.lastfm.ui.utils.GridColumnDecorator
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_top_albums.*
import javax.inject.Inject

class AlbumsFragment : BaseFragment(), TopAlbumsAdapterCallback {

    @Inject
    lateinit var albumsViewModel: AlbumsViewModel

    private val args: AlbumsFragmentArgs by navArgs()

    private val adapter = TopAlbumsAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_top_albums, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupView()
        setupRecyclerView()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> findNavController().popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onAlbumSelected(album: Album) {
        val direction = AlbumsFragmentDirections.actionAlbumsFragmentToAlbumDetailsFragment(album)
        findNavController().navigate(direction)
    }

    override fun onAlbumLiked(album: Album) {
        albumsViewModel.saveAlbum(album)
    }

    private fun setupToolbar() {
        if (activity is AppCompatActivity) {
            val act = activity as AppCompatActivity
            setHasOptionsMenu(true)
            act.setSupportActionBar(topAlbumsToolbar)
            act.supportActionBar?.title = args.artist.name
            act.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupView() {
        Glide.with(this)
            .load(args.artist.getImage())
            .error(R.drawable.ic_music_note)
            .centerCrop()
            .placeholder(R.drawable.ic_music_note)
            .into(topAlbumsArtistImage)
    }

    private fun setupRecyclerView() {
        val decorator = GridColumnDecorator(resources.getDimensionPixelSize(R.dimen.default_padding_tiny))
        topAlbumsList.layoutManager = GridLayoutManager(context, COLUMNS_COUNT, RecyclerView.VERTICAL, false)
        topAlbumsList.adapter = adapter
        topAlbumsList.addItemDecoration(decorator)
    }

    private fun setupObservers() {
        albumsViewModel.getNetworkState().observe(this, Observer {
            when (it.status) {
                Status.RUNNING -> {
                    topAlbumsProgress.visibility = View.VISIBLE
                    albumsEmptyState?.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    topAlbumsProgress.visibility = View.GONE
                    searchEmptyState?.visibility = View.GONE
                }
                Status.FAILED -> {
                    topAlbumsProgress.visibility = View.GONE
                    albumsEmptyState?.visibility = View.VISIBLE
                }
                Status.EMPTY -> {
                    topAlbumsProgress.visibility = View.GONE
                    albumsEmptyState?.visibility = View.VISIBLE
                }
            }
        })
        albumsViewModel.getTopAlbums(args.artist).observe(this, Observer {
            adapter.submitList(it)
        })
    }

    companion object {
        const val COLUMNS_COUNT = 2
    }
}