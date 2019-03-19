package com.roman.kubik.lastfm.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.roman.kubik.lastfm.R
import com.roman.kubik.lastfm.repository.model.Album
import com.roman.kubik.lastfm.repository.model.Artist
import com.roman.kubik.lastfm.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_album_details.*
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class AlbumDetailsFragment: BaseFragment() {

    private val args: AlbumDetailsFragmentArgs by navArgs()
    private val adapter = TracksAdapter()

    @Inject
    lateinit var viewModel: AlbumDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_album_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupView()
        setupAlbum(args.album)
        viewModel.getAlbumDetails(args.album).observe(this, Observer {
            setupAlbum(it)
        })
    }

    private fun setupToolbar() {
        if (activity is AppCompatActivity) {
            val act = activity as AppCompatActivity
            act.setSupportActionBar(detailsToolbar)
            act.supportActionBar?.title = "Details"
            act.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupView() {
        val layoutManager = LinearLayoutManager(context)
        detailsAlbumTrackList.layoutManager = layoutManager
        detailsAlbumTrackList.adapter = adapter
        detailsSave.setOnClickListener {
            viewModel.saveAlbum()
        }
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
        adapter.submitList(album.tracks)
        if (album.isLiked) {
            detailsSave.setImageResource(R.drawable.ic_star)
        } else {
            detailsSave.setImageResource(R.drawable.ic_star_border)
        }
    }
}