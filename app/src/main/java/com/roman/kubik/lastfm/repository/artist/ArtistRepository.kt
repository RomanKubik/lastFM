package com.roman.kubik.lastfm.repository.artist

import androidx.lifecycle.LiveData
import com.roman.kubik.lastfm.api.model.Artist

interface ArtistRepository {

    fun getArtists(name: String): LiveData<List<Artist>>
}