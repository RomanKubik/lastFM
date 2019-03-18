package com.roman.kubik.lastfm.repository.albums

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.roman.kubik.lastfm.api.LastFmRestService
import com.roman.kubik.lastfm.api.LastFmRestService.Companion.DEFAULT_PAGE_SIZE
import com.roman.kubik.lastfm.persistence.AlbumDao
import com.roman.kubik.lastfm.persistence.service.PersistenceService
import com.roman.kubik.lastfm.repository.model.Album
import com.roman.kubik.lastfm.repository.model.Artist
import com.roman.kubik.lastfm.repository.model.Listing
import com.roman.kubik.lastfm.util.MainThreadExecutor
import javax.inject.Inject

class AlbumsRepositoryImpl @Inject constructor(
    private val restService: LastFmRestService,
    private val executor: MainThreadExecutor,
    private val persistenceService: PersistenceService,
    private val albumDao: AlbumDao
) : AlbumsRepository {

    override fun getTopAlbums(artistId: String): Listing<Album> {
        val data = MediatorLiveData<PagedList<Album>>()
        val dataSource = AlbumsDataSource(restService, albumDao, artistId)

        val list = PagedList.Builder(dataSource, getAlbumsPagedListConfig())
            .setNotifyExecutor(executor)
            .setFetchExecutor(executor)
            .build()

        data.value = list

        val cachedLiveData = Transformations.map(albumDao.getArtistAlbum(artistId)) {
            val albums = ArrayList<Album>()
            for (album in it)
                albums.add(mapFromDbEntity(album))
            albums
        }

        data.addSource(cachedLiveData) {
            data.value?.addAll(it)
        }

        return Listing(data, dataSource.getNetworkState())
    }

    override fun saveAlbum(artist: Artist, album: Album) = persistenceService.saveAlbum(artist, album)


    override fun deleteAlbum(artist: Artist, album: Album) = persistenceService.deleteAlbum(artist, album)


    private fun getAlbumsPagedListConfig(): PagedList.Config {
        return PagedList.Config.Builder()
            .setPageSize(DEFAULT_PAGE_SIZE)
            .setInitialLoadSizeHint(DEFAULT_PAGE_SIZE * 2)
            .setEnablePlaceholders(true)
            .build()
    }

    private fun mapFromDbEntity(album: com.roman.kubik.lastfm.persistence.model.Album) =
        Album(album.id, album.name, album.imagePath, true)
}