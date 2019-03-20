package com.roman.kubik.lastfm.ui.details

import androidx.lifecycle.LiveData
import com.roman.kubik.lastfm.repository.model.Album

interface AlbumDetailsViewModel {
    fun getAlbumDetails(album: Album): LiveData<Album>

    fun saveAlbum()
}