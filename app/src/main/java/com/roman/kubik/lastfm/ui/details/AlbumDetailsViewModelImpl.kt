package com.roman.kubik.lastfm.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.roman.kubik.lastfm.repository.albums.AlbumRepository
import com.roman.kubik.lastfm.repository.artist.ArtistRepository
import com.roman.kubik.lastfm.repository.model.Album
import com.roman.kubik.lastfm.repository.model.Artist
import javax.inject.Inject

class AlbumDetailsViewModelImpl @Inject constructor(
    private val albumRepository: AlbumRepository
) : ViewModel(), AlbumDetailsViewModel {

    override fun getAlbumDetails(album: Album): LiveData<Album> {
        return albumRepository.getAlbumDetails(album)
    }

}