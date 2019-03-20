package com.roman.kubik.lastfm.ui.search

import com.roman.kubik.lastfm.repository.model.Artist

interface ArtistAdapterCallback {

    fun onArtistSelected(artist: Artist)
}