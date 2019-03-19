package com.roman.kubik.lastfm.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.roman.kubik.lastfm.R
import com.roman.kubik.lastfm.repository.model.Album
import com.roman.kubik.lastfm.repository.model.Artist
import com.roman.kubik.lastfm.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_album_details.*
import javax.inject.Inject

class AlbumDetailsFragment: BaseFragment() {

    private val args: AlbumDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var viewModel: AlbumDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_album_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupAlbum(args.album)
        viewModel.getAlbumDetails(args.album).observe(this, Observer {
            setupAlbum(it)
        })
    }

    private fun setupAlbum(album: Album) {
        Glide.with(this)
            .load(album.imagePath)
            .error(R.drawable.ic_music_note)
            .fitCenter()
            .placeholder(R.drawable.ic_music_note)
            .into(detailsAlbumImage)
        detailsAlbumName.text = album.name
        detailsTotalSonsCount.text = album.tracks.size.toString()
        album.artist?.let {
            detailsArtistName.text = it.name
            Glide.with(this)
                .load(it.imagePath)
                .error(R.drawable.ic_music_note)
                .fitCenter()
                .placeholder(R.drawable.ic_music_note)
                .into(detailsArtistImage)
        }
    }
}