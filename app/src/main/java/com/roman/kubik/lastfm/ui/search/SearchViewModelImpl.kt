package com.roman.kubik.lastfm.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.roman.kubik.lastfm.api.model.Artist
import com.roman.kubik.lastfm.repository.artist.ArtistRepository
import javax.inject.Inject


class SearchViewModelImpl @Inject constructor(private val artistRepository: ArtistRepository) : ViewModel(), SearchViewModel {

    private var artists = MediatorLiveData<PagedList<Artist>>()

    override fun search(name: String) {
        artists.addSource(artistRepository.getArtists(name)){
            artists.value = it
        }
    }

    override fun getArtists(): LiveData<PagedList<Artist>> = artists

}