package com.roman.kubik.lastfm.persistence.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import com.roman.kubik.lastfm.persistence.AlbumDao
import com.roman.kubik.lastfm.persistence.ArtistDao
import com.roman.kubik.lastfm.persistence.TrackDao
import com.roman.kubik.lastfm.persistence.model.AlbumEntity
import com.roman.kubik.lastfm.persistence.model.ArtistEntity
import com.roman.kubik.lastfm.persistence.model.TrackEntity
import com.roman.kubik.lastfm.repository.mapper.*
import com.roman.kubik.lastfm.repository.model.Album
import com.roman.kubik.lastfm.repository.model.Artist
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

    override fun getAlbum(album: Album) = zipAlbumObject(
        albumDao.getAlbumById(album.id),
        artistDao.getArtistByAlbumId(album.id),
        trackDao.getAlbumTracks(album.id)
    )

    override fun getSavedAlbums(): LiveData<List<Album>> {
        val data = MediatorLiveData<List<Album>>()
        data.addSource(albumDao.getAllAlbums()) {
            if (!it.isNullOrEmpty()) {
                val result = ArrayList<Album>()
                for (album in it) {
                    val source = artistDao.getArtistById(album.artistId)
                    data.addSource(source) { artist ->
                        data.removeSource(source)
                        result.add(album.toAlbum(artist))
                        data.value = result
                    }
                }
            }
        }
        return data
    }

    private fun zipAlbumObject(
        a: LiveData<AlbumEntity>,
        b: LiveData<ArtistEntity>,
        c: LiveData<List<TrackEntity>>
    ): LiveData<Album?> {
        return MediatorLiveData<Album>().apply {
            var lastA: AlbumEntity? = null
            var lastB: ArtistEntity? = null
            var lastC: List<TrackEntity>? = null

            fun update() {
                val localLastA = lastA
                val localLastB = lastB
                val localLastC = lastC
                if (localLastA != null && localLastB != null && localLastC != null) {
                    val album = localLastA.toAlbum(localLastB).copy(tracks = localLastC.map(TrackEntity::toTrack))
                    this.value = album
                }
            }

            addSource(a) {
                lastA = it
                if (it == null) {
                    this.value = null
                } else {
                    update()
                }
            }
            addSource(b) {
                lastB = it
                update()
            }
            addSource(c) {
                lastC = it
                update()
            }
        }
    }
}