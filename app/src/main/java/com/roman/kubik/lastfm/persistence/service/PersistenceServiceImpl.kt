package com.roman.kubik.lastfm.persistence.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.roman.kubik.lastfm.persistence.AlbumDao
import com.roman.kubik.lastfm.persistence.ArtistDao
import com.roman.kubik.lastfm.persistence.TrackDao
import com.roman.kubik.lastfm.persistence.model.AlbumEntity
import com.roman.kubik.lastfm.persistence.model.ArtistEntity
import com.roman.kubik.lastfm.persistence.model.TrackEntity
import com.roman.kubik.lastfm.repository.mapper.toAlbum
import com.roman.kubik.lastfm.repository.mapper.toArtist
import com.roman.kubik.lastfm.repository.mapper.toTrack
import com.roman.kubik.lastfm.repository.model.Album
import com.roman.kubik.lastfm.repository.model.Artist
import com.roman.kubik.lastfm.repository.model.DatabaseState
import java.util.concurrent.Executors
import javax.inject.Inject

class PersistenceServiceImpl @Inject constructor(
    private val artistDao: ArtistDao,
    private val albumDao: AlbumDao,
    private val trackDao: TrackDao
) :
    PersistenceService {

    private val executor = Executors.newSingleThreadExecutor()
    private val data = MutableLiveData<DatabaseState>()

    override fun saveAlbum(album: Album): LiveData<DatabaseState> {
        data.value = DatabaseState.LOADING
        executor.execute {
            artistDao.insertArtistAndAlbum(mapToDbEntity(album.artist!!), mapToDbEntity(album, album.artist!!))
            data.postValue(DatabaseState.LOADED)
        }
        return data
    }

    override fun deleteAlbum(album: Album): LiveData<DatabaseState> {
        data.value = DatabaseState.LOADING
        executor.execute {
            albumDao.deleteAlbum(mapToDbEntity(album, album.artist!!))
            data.value = DatabaseState.LOADED
        }
        return data
    }

    override fun getAlbum(album: Album): LiveData<Album?> {
        val data = MediatorLiveData<Album>()
        data.addSource(albumDao.getAlbumById(album.id)) {
            if (it != null) {
                val resultAlbum = it.toAlbum()
                data.value = resultAlbum
                data.addSource(artistDao.getArtistById(it.artistId)) { artist ->
                    data.value = resultAlbum.copy(artist = artist?.toArtist())
                }
                data.addSource(trackDao.getAlbumTracks(album.id)) { tracks ->
                    data.value = resultAlbum.copy(tracks = tracks?.map(TrackEntity::toTrack) ?: ArrayList())
                }
            } else {
                data.value = null
            }
        }
        return data
    }

    private fun mapToDbEntity(album: Album, artist: Artist) =
        AlbumEntity(album.id, album.name, album.imagePath, artist.id)

    private fun mapToDbEntity(artist: Artist) = ArtistEntity(artist.id, artist.name, artist.imagePath)
    private fun mapFromDbEntity(album: AlbumEntity?): Album? {
        return if (album != null) {
            Album(album.id, album.name, album.imagePath, null, true)
        } else {
            null
        }
    }

    private fun mapFromDbEntity(artist: ArtistEntity) = Artist(artist.id, artist.name, artist.imagePath)
}