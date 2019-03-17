package com.roman.kubik.lastfm.ui.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.roman.kubik.lastfm.R
import com.roman.kubik.lastfm.repository.model.Status
import com.roman.kubik.lastfm.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_top_albums.*
import javax.inject.Inject

class AlbumsFragment : BaseFragment() {

    @Inject
    lateinit var albumsViewModel: AlbumsViewModel

    val args: AlbumsFragmentArgs by navArgs()

    private val adapter = TopAlbumsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_top_albums, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupView()
        setupRecyclerView()
        setupObservers()
    }

    private fun setupView() {
        Glide.with(this)
            .load(args.artistImage)
            .error(R.drawable.ic_music_note)
            .fitCenter()
            .placeholder(R.drawable.ic_music_note)
            .into(topAlbumsArtistImage)
    }

    private fun setupRecyclerView() {
        topAlbumsList.layoutManager = GridLayoutManager(context, COLUMNS_COUNT, RecyclerView.VERTICAL, false)
        topAlbumsList.adapter = adapter
    }

    private fun setupObservers() {
        albumsViewModel.getNetworkState().observe(this, Observer {
            when (it.status) {
                Status.RUNNING -> topAlbumsProgress.visibility = View.VISIBLE
                Status.SUCCESS -> topAlbumsProgress.visibility = View.GONE
                Status.FAILED -> topAlbumsProgress.visibility = View.GONE
            }
        })
        albumsViewModel.getTopAlbums(args.artistId).observe(this, Observer {
            adapter.submitList(it)
        })
    }

    companion object {
        const val COLUMNS_COUNT = 2
    }
}