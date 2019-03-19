package com.roman.kubik.lastfm.persistence.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.roman.kubik.lastfm.persistence.AlbumDao
import com.roman.kubik.lastfm.persistence.ArtistDao
import com.roman.kubik.lastfm.persistence.TrackDao
import com.roman.kubik.lastfm.persistence.model.TrackEntity
import com.roman.kubik.lastfm.repository.mapper.*
import com.roman.kubik.lastfm.repository.model.Album
import com.roman.kubik.lastfm.repository.model.Track
import java.util.concurrent.Executors
import javax.inject.Inject

class PersistenceServiceImpl @Inject constructor(
    private val fileSaver: FileSaver,
    private val artistDao: ArtistDao,
    private val albumDao: AlbumDao,
    private val trackDao: TrackDao
) :
    PersistenceService {

    private val executor = Executors.newSingleThreadExecutor()

    override fun saveAlbum(album: Album) {
        executor.execute {
            album.artist?.let { artist ->

                val albumImagePath = fileSaver.loadFile(album.imagePath ?: "", album.id)
                val artistImagePath = fileSaver.loadFile(artist.imagePath ?: "", artist.id)
                artistDao.insertArtistAlbumAndTracks(
                    artist.toArtistEntity(artistImagePath),
                    album.toAlbumEntity(albumImagePath),
                    album.tracks.map(Track::toTrackEntity)
                )
            }
        }
    }

    override fun deleteAlbum(album: Album) {
        executor.execute {
            albumDao.deleteAlbum(album.toAlbumEntity())
            fileSaver.deleteFile(album.imagePath)
            if (albumDao.getArtistAlbumsSync(album.artist!!.id).isNullOrEmpty()) {
                artistDao.deleteArtist(album.artist.toArtistEntity())
                fileSaver.deleteFile(album.artist.imagePath)
            }
        }
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
}