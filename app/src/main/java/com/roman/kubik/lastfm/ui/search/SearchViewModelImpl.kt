package com.roman.kubik.lastfm.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.roman.kubik.lastfm.api.model.Artist
import com.roman.kubik.lastfm.repository.artist.ArtistRepository
import com.roman.kubik.lastfm.repository.model.NetworkState
import javax.inject.Inject


class SearchViewModelImpl @Inject constructor(private val artistRepository: ArtistRepository) : ViewModel(), SearchViewModel {

    private var artists = MediatorLiveData<PagedList<Artist>>()
    private var networkData = MediatorLiveData<NetworkState>()

    override fun search(name: String) {
        val listing = artistRepository.getArtists(name)
        artists.addSource(listing.pagedList){
            artists.value = it
        }
        networkData.addSource(listing.networkState) {
            networkData.value = it
        }
    }

    override fun getArtists(): LiveData<PagedList<Artist>> = artists

    override fun getNetworkState(): LiveData<NetworkState> = networkData

}