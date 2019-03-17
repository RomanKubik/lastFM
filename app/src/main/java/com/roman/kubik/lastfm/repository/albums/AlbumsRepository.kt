package com.roman.kubik.lastfm.repository.albums

import com.roman.kubik.lastfm.repository.model.Album
import com.roman.kubik.lastfm.repository.model.Listing

interface AlbumsRepository {

    fun getTopAlbums(artistId: String): Listing<Album>

    fun saveAlbum(album: Album)

    fun removeAlbum(album: Album)

}