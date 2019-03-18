package com.roman.kubik.lastfm.repository.albums

import androidx.lifecycle.LiveData
import com.roman.kubik.lastfm.repository.model.Album
import com.roman.kubik.lastfm.repository.model.Artist
import com.roman.kubik.lastfm.repository.model.DatabaseState
import com.roman.kubik.lastfm.repository.model.Listing

interface AlbumsRepository {

    fun getTopAlbums(artistId: String): Listing<Album>

    fun saveAlbum(artist: Artist, album: Album): LiveData<DatabaseState>

    fun deleteAlbum(artist: Artist, album: Album): LiveData<DatabaseState>

}