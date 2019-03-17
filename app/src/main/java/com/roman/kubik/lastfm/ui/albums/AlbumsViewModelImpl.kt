package com.roman.kubik.lastfm.ui.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.roman.kubik.lastfm.repository.albums.AlbumsRepository
import com.roman.kubik.lastfm.repository.model.Album
import com.roman.kubik.lastfm.repository.model.NetworkState
import javax.inject.Inject

class AlbumsViewModelImpl @Inject constructor(private val albumsRepository: AlbumsRepository): ViewModel(), AlbumsViewModel {

    private var networkData = MediatorLiveData<NetworkState>()

    override fun getTopAlbums(artistId: String): LiveData<PagedList<Album>> {
        val response = albumsRepository.getTopAlbums(artistId)
        networkData.addSource(response.networkState) {
            networkData.value = it
        }
        return response.pagedList
    }

    override fun getNetworkState(): LiveData<NetworkState> = networkData

}