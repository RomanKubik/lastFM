package com.roman.kubik.lastfm.persistence.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.roman.kubik.lastfm.persistence.AlbumDao
import com.roman.kubik.lastfm.persistence.ArtistDao
import com.roman.kubik.lastfm.repository.model.Album
import com.roman.kubik.lastfm.repository.model.Artist
import com.roman.kubik.lastfm.repository.model.DatabaseState
import java.util.concurrent.Executors
import javax.inject.Inject

class PersistenceServiceImpl @Inject constructor(private val artistDao: ArtistDao, private val albumDao: AlbumDao) :
    PersistenceService {

    private val executor = Executors.newSingleThreadExecutor()
    private val data = MutableLiveData<DatabaseState>()

    override fun saveAlbum(album: Album, artist: Artist): LiveData<DatabaseState> {
        data.value = DatabaseState.LOADING
        executor.execute {
            artistDao.insertArtistAndAlbum(mapToDbEntity(artist), mapToDbEntity(album, artist))
            data.value = DatabaseState.LOADED
        }
        return data
    }

    override fun removeAlbum(album: Album, artist: Artist): LiveData<DatabaseState> {
        data.value = DatabaseState.LOADING
        executor.execute {
            albumDao.deleteAlbum(mapToDbEntity(album, artist))
            data.value = DatabaseState.LOADED
        }
        return data
    }

    private fun mapToDbEntity(album: Album, artist: Artist) = com.roman.kubik.lastfm.persistence.model.Album(album.id, album.name, album.imagePath, artist.id)
    private fun mapToDbEntity(artist: Artist) = com.roman.kubik.lastfm.persistence.model.Artist(artist.id, artist.name, artist.imagePath)

}