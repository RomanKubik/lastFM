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
import com.roman.kubik.lastfm.repository.model.Album
import com.roman.kubik.lastfm.repository.model.Status
import com.roman.kubik.lastfm.ui.base.BaseFragment
import com.roman.kubik.lastfm.ui.utils.GridColumnDecorator
import kotlinx.android.synthetic.main.fragment_top_albums.*
import javax.inject.Inject

class AlbumsFragment : BaseFragment(), TopAlbumsAdapterCallback {

    @Inject
    lateinit var albumsViewModel: AlbumsViewModel

    private val args: AlbumsFragmentArgs by navArgs()

    private val adapter = TopAlbumsAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_top_albums, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupView()
        setupRecyclerView()
        setupObservers()
    }

    override fun onAlbumSelected(album: Album) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAlbumLiked(album: Album) {
        albumsViewModel.saveAlbum(album).observe(this, Observer {
            when (it.status) {
                Status.RUNNING -> topAlbumsProgress.visibility = View.VISIBLE
                Status.SUCCESS -> topAlbumsProgress.visibility = View.GONE
                Status.FAILED -> topAlbumsProgress.visibility = View.GONE
            }
        })
    }

    private fun setupView() {
        Glide.with(this)
            .load(args.artist.imagePath)
            .error(R.drawable.ic_music_note)
            .fitCenter()
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
                Status.RUNNING -> topAlbumsProgress.visibility = View.VISIBLE
                Status.SUCCESS -> topAlbumsProgress.visibility = View.GONE
                Status.FAILED -> topAlbumsProgress.visibility = View.GONE
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