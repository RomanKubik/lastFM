package com.roman.kubik.lastfm.ui.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.roman.kubik.lastfm.repository.albums.AlbumsRepository
import com.roman.kubik.lastfm.repository.model.Album
import com.roman.kubik.lastfm.repository.model.Artist
import com.roman.kubik.lastfm.repository.model.NetworkState
import javax.inject.Inject

class AlbumsViewModelImpl @Inject constructor(private val albumsRepository: AlbumsRepository): ViewModel(), AlbumsViewModel {

    private lateinit var artist: Artist
    private var networkData = MediatorLiveData<NetworkState>()

    override fun getTopAlbums(artist: Artist): LiveData<PagedList<Album>> {
        this.artist = artist
        val response = albumsRepository.getTopAlbums(artist.id)
        networkData.addSource(response.networkState) {
            networkData.value = it
        }
        return response.pagedList
    }

    override fun getNetworkState(): LiveData<NetworkState> = networkData

    override fun saveAlbum(album: Album) = albumsRepository.saveAlbum(artist, album)

    override fun deleteAlbum(album: Album) = albumsRepository.deleteAlbum(artist, album)

}