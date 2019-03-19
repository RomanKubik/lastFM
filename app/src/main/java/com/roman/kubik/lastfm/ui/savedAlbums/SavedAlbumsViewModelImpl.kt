package com.roman.kubik.lastfm.ui.savedAlbums

import androidx.lifecycle.ViewModel
import com.roman.kubik.lastfm.repository.albums.AlbumRepository
import javax.inject.Inject

class SavedAlbumsViewModelImpl @Inject constructor(private val albumRepository: AlbumRepository) : ViewModel(), SavedAlbumsViewModel {

    override fun getSavedAlbums() = albumRepository.getSavedAlbums()


}