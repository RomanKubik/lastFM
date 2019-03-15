package com.roman.kubik.lastfm.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.roman.kubik.lastfm.api.LastFmRestService
import com.roman.kubik.lastfm.api.model.Artist
import com.roman.kubik.lastfm.repository.artist.ArtistDataSource
import com.roman.kubik.lastfm.repository.artist.ArtistRepository
import com.roman.kubik.lastfm.util.MainThreadExecutor
import javax.inject.Inject


class SearchViewModelImpl @Inject constructor(private val artistRepository: ArtistRepository,
                                              private val restService: LastFmRestService,
                                              private val executor: MainThreadExecutor
) : ViewModel(), SearchViewModel {

    private var artists = MediatorLiveData<PagedList<Artist>>()

    override fun search(name: String) {
        val dataSource = ArtistDataSource(restService, name)

        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setInitialLoadSizeHint(30 * 2)
            .setEnablePlaceholders(true)
            .build()

        val list = PagedList.Builder(dataSource, config)
            .setNotifyExecutor(executor)
            .setFetchExecutor(executor)
            .build()
        artists.value = list
//        artists.addSource(artistRepository.getArtists(name)){
//
//        }
    }

    override fun getArtists(): LiveData<PagedList<Artist>> = artists

}