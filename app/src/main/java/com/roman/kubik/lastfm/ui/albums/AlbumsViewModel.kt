package com.roman.kubik.lastfm.ui.albums

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.roman.kubik.lastfm.repository.model.Album
import com.roman.kubik.lastfm.repository.model.NetworkState

interface AlbumsViewModel {

    fun getTopAlbums(artistId: String): LiveData<PagedList<Album>>

    fun getNetworkState(): LiveData<NetworkState>
}