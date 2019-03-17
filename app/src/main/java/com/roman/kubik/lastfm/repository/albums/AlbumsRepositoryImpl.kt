package com.roman.kubik.lastfm.repository.albums

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.roman.kubik.lastfm.api.LastFmRestService
import com.roman.kubik.lastfm.api.LastFmRestService.Companion.DEFAULT_PAGE_SIZE
import com.roman.kubik.lastfm.api.model.Album
import com.roman.kubik.lastfm.repository.model.Listing
import com.roman.kubik.lastfm.util.MainThreadExecutor
import javax.inject.Inject

class AlbumsRepositoryImpl @Inject constructor(private val restService: LastFmRestService, private val executor: MainThreadExecutor) : AlbumsRepository {

    override fun getTopAlbums(artistId: String): Listing<Album> {
        val data = MutableLiveData<PagedList<Album>>()
        val dataSource = AlbumsDataSource(restService, artistId)

        val list = PagedList.Builder(dataSource, getAlbumsPagedListConfig())
            .setNotifyExecutor(executor)
            .setFetchExecutor(executor)
            .build()

        data.value = list

        return Listing(data, dataSource.getNetworkState())
    }

    private fun getAlbumsPagedListConfig(): PagedList.Config {
        return PagedList.Config.Builder()
            .setPageSize(DEFAULT_PAGE_SIZE)
            .setInitialLoadSizeHint(DEFAULT_PAGE_SIZE * 2)
            .setEnablePlaceholders(true)
            .build()
    }
}