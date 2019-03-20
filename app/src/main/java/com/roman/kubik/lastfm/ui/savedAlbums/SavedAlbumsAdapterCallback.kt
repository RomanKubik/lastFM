package com.roman.kubik.lastfm.ui.savedAlbums

import com.roman.kubik.lastfm.repository.model.Album

interface SavedAlbumsAdapterCallback {

    fun onAlbumSelected(album: Album)

}