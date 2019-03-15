package com.roman.kubik.lastfm.repository.artist

import com.roman.kubik.lastfm.api.model.Artist
import com.roman.kubik.lastfm.repository.model.Listing

interface ArtistRepository {

    fun getArtists(name: String): Listing<Artist>
}