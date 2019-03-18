package com.roman.kubik.lastfm.ui.albums

import com.roman.kubik.lastfm.repository.model.Album

interface TopAlbumsAdapterCallback {

    fun onAlbumSelected(album: Album)

    fun onAlbumLiked(album: Album)

}