package com.roman.kubik.lastfm.repository.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.roman.kubik.lastfm.api.LastFmRestService
import com.roman.kubik.lastfm.api.LastFmRestService.Companion.DEFAULT_PAGE_SIZE
import com.roman.kubik.lastfm.api.model.AlbumInfoResponse
import com.roman.kubik.lastfm.persistence.AlbumDao
import com.roman.kubik.lastfm.persistence.service.PersistenceService
import com.roman.kubik.lastfm.repository.mapper.toAlbum
import com.roman.kubik.lastfm.repository.model.Album
import com.roman.kubik.lastfm.repository.model.Artist
import com.roman.kubik.lastfm.repository.model.Listing
import com.roman.kubik.lastfm.util.MainThreadExecutor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val restService: LastFmRestService,
    private val executor: MainThreadExecutor,
    private val persistenceService: PersistenceService
) : AlbumRepository {

    override fun getTopAlbums(artist: Artist): Listing<Album> {
        val data = MediatorLiveData<PagedList<Album>>()
        val dataSource = AlbumsDataSource(restService, artist)

        val list = PagedList.Builder(dataSource, getAlbumsPagedListConfig())
            .setNotifyExecutor(executor)
            .setFetchExecutor(executor)
            .build()

        data.value = list

        return Listing(data, dataSource.getNetworkState())
    }

    override fun getAlbumDetails(album: Album): LiveData<Album> {
        val albumData = MediatorLiveData<Album>()
        albumData.addSource(persistenceService.getAlbum(album)){
            if (it != null) {
                albumData.value = it
            } else {
                albumData.addSource(performNetworkCall(album)) { a ->
                    albumData.value = a
                }
            }
        }
        return albumData
    }

    override fun saveAlbum(album: Album) = persistenceService.saveAlbum(album)


    override fun deleteAlbum(album: Album) = persistenceService.deleteAlbum(album)


    private fun getAlbumsPagedListConfig(): PagedList.Config {
        return PagedList.Config.Builder()
            .setPageSize(DEFAULT_PAGE_SIZE)
            .setInitialLoadSizeHint(DEFAULT_PAGE_SIZE * 2)
            .setEnablePlaceholders(true)
            .build()
    }

    private fun performNetworkCall(album: Album): LiveData<Album> {
        val data = MutableLiveData<Album>()
        restService.getAlbumInfo(album.id).enqueue(object : Callback<AlbumInfoResponse> {
            override fun onFailure(call: Call<AlbumInfoResponse>, t: Throwable) {
                System.out.print("error")
            }

            override fun onResponse(call: Call<AlbumInfoResponse>, response: Response<AlbumInfoResponse>) {
                val r = response.body()?.albumModel?.toAlbum(album.artist)
                if (r != null) {
                    data.value = r
                } else {
                    onFailure(call, HttpException(response))
                }
            }

        })
        return data
    }

}