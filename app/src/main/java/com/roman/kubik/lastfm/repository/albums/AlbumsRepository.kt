package com.roman.kubik.lastfm.repository.albums

import com.roman.kubik.lastfm.api.model.Album
import com.roman.kubik.lastfm.repository.model.Listing

interface AlbumsRepository {

    fun getTopAlbums(artistId: String): Listing<Album>

}