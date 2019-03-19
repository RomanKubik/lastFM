package com.roman.kubik.lastfm.repository.albums

import androidx.lifecycle.LiveData
import com.roman.kubik.lastfm.repository.model.Album
import com.roman.kubik.lastfm.repository.model.Artist
import com.roman.kubik.lastfm.repository.model.DatabaseState
import com.roman.kubik.lastfm.repository.model.Listing

interface AlbumRepository {

    fun getTopAlbums(artist: Artist): Listing<Album>

    fun getAlbumDetails(album: Album): LiveData<Album>

    fun saveAlbum(album: Album): LiveData<DatabaseState>

    fun deleteAlbum(album: Album): LiveData<DatabaseState>

}