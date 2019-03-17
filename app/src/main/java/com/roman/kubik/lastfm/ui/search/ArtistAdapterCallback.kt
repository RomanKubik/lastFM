package com.roman.kubik.lastfm.ui.search

import com.roman.kubik.lastfm.api.model.Artist

interface ArtistAdapterCallback {

    fun onArtistSelected(artist: Artist)
}