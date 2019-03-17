package com.roman.kubik.lastfm.persistence.service

import androidx.lifecycle.LiveData
import com.roman.kubik.lastfm.repository.model.Album
import com.roman.kubik.lastfm.repository.model.Artist
import com.roman.kubik.lastfm.repository.model.DatabaseState

interface PersistenceService {

    fun saveAlbum(album: Album, artist: Artist): LiveData<DatabaseState>

    fun removeAlbum(album: Album, artist: Artist): LiveData<DatabaseState>
}