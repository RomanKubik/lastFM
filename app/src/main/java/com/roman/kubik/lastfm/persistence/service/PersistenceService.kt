package com.roman.kubik.lastfm.persistence.service

import androidx.lifecycle.LiveData
import com.roman.kubik.lastfm.repository.model.Album

interface PersistenceService {

    fun saveAlbum(album: Album)

    fun deleteAlbum(album: Album)

    fun getAlbum(album: Album): LiveData<Album?>

    fun getSavedAlbums(): LiveData<List<Album>>
}