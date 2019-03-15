package com.roman.kubik.lastfm.repository.artist

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.roman.kubik.lastfm.api.LastFmRestService
import com.roman.kubik.lastfm.api.LastFmRestService.Companion.DEFAULT_PAGE_SIZE
import com.roman.kubik.lastfm.api.model.Artist
import com.roman.kubik.lastfm.repository.model.Listing
import com.roman.kubik.lastfm.util.MainThreadExecutor
import javax.inject.Inject


class ArtistRepositoryImpl @Inject constructor(private val restService: LastFmRestService, private val executor: MainThreadExecutor) : ArtistRepository {

    override fun getArtists(name: String): Listing<Artist> {
        val data = MutableLiveData<PagedList<Artist>>()
        val dataSource = ArtistDataSource(restService, name)

        val list = PagedList.Builder(dataSource, getArtistPagedListConfig())
            .setNotifyExecutor(executor)
            .setFetchExecutor(executor)
            .build()

        data.value = list

        return Listing(data, dataSource.getNetworkState())
    }

    private fun getArtistPagedListConfig(): PagedList.Config {
        return PagedList.Config.Builder()
            .setPageSize(DEFAULT_PAGE_SIZE)
            .setInitialLoadSizeHint(DEFAULT_PAGE_SIZE * 2)
            .setEnablePlaceholders(true)
            .build()
    }
}