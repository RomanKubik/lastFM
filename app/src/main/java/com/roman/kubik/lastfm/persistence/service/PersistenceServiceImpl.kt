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



    override fun saveAlbum(artist: Artist, album: Album): LiveData<DatabaseState> {
        data.value = DatabaseState.LOADING
        executor.execute {
            artistDao.insertArtistAndAlbum(mapToDbEntity(artist), mapToDbEntity(album, artist))
            data.value = DatabaseState.LOADED
        }
        return data
    }

    override fun deleteAlbum(artist: Artist, album: Album): LiveData<DatabaseState> {
        data.value = DatabaseState.LOADING
        executor.execute {
            albumDao.deleteAlbum(mapToDbEntity(album, artist))
            data.value = DatabaseState.LOADED
        }
        return data
    }

    private val executor = Executors.newSingleThreadExecutor()
    private val data = MutableLiveData<DatabaseState>()

    private fun mapToDbEntity(album: Album, artist: Artist) = com.roman.kubik.lastfm.persistence.model.Album(album.id, album.name, album.imagePath, artist.id)
    private fun mapToDbEntity(artist: Artist) = com.roman.kubik.lastfm.persistence.model.Artist(artist.id, artist.name, artist.imagePath)
    private fun mapFromDbEntity(album: com.roman.kubik.lastfm.persistence.model.Album) = Album(album.id, album.name, album.imagePath, true)
    private fun mapFromDbEntity(artist: com.roman.kubik.lastfm.persistence.model.Artist) = Artist(artist.id, artist.name, artist.imagePath)
}