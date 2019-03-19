package com.roman.kubik.lastfm.ui.albums

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.roman.kubik.lastfm.repository.model.Album
import com.roman.kubik.lastfm.repository.model.Artist
import com.roman.kubik.lastfm.repository.model.DatabaseState
import com.roman.kubik.lastfm.repository.model.NetworkState

interface AlbumsViewModel {

    fun getTopAlbums(artist: Artist): LiveData<PagedList<Album>>

    fun getNetworkState(): LiveData<NetworkState>

    fun saveAlbum(album: Album)

    fun deleteAlbum(album: Album)
}