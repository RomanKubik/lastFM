package com.roman.kubik.lastfm.ui.search

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.roman.kubik.lastfm.api.model.Artist
import com.roman.kubik.lastfm.repository.model.NetworkState

interface SearchViewModel {

    fun search(name: String)

    fun getArtists(): LiveData<PagedList<Artist>>

    fun getNetworkState(): LiveData<NetworkState>

}