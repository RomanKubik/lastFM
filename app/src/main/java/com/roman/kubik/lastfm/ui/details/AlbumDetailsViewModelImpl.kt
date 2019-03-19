package com.roman.kubik.lastfm.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.roman.kubik.lastfm.repository.albums.AlbumRepository
import com.roman.kubik.lastfm.repository.artist.ArtistRepository
import com.roman.kubik.lastfm.repository.model.Album
import com.roman.kubik.lastfm.repository.model.Artist
import javax.inject.Inject

class AlbumDetailsViewModelImpl @Inject constructor(
    private val albumRepository: AlbumRepository
) : ViewModel(), AlbumDetailsViewModel {

    private var albumData = MediatorLiveData<Album>()

    override fun getAlbumDetails(album: Album): LiveData<Album> {
        albumData.addSource(albumRepository.getAlbumDetails(album)) {
            albumData.value = it
        }
        return albumData
    }

    override fun saveAlbum() {
        albumData.value?.let {
            if (it.isLiked) {
                albumRepository.deleteAlbum(it)
            } else {
                albumRepository.saveAlbum(it)
            }
            albumData.value = it.copy(isLiked = !it.isLiked)
        }
    }
}