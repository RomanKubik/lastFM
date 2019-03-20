package com.roman.kubik.lastfm.ui.savedAlbums

import androidx.lifecycle.LiveData
import com.roman.kubik.lastfm.repository.model.Album

interface SavedAlbumsViewModel {

    fun getSavedAlbums(): LiveData<List<Album>>
}