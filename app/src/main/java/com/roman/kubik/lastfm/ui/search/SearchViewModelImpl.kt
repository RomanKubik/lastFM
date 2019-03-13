package com.roman.kubik.lastfm.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.roman.kubik.lastfm.api.Foo
import com.roman.kubik.lastfm.api.LastFmRestService
import com.roman.kubik.lastfm.api.model.Artist
import com.roman.kubik.lastfm.repository.ArtistRepository
import javax.inject.Inject

class SearchViewModelImpl @Inject constructor(private val artistRepository: ArtistRepository) : ViewModel(), SearchViewModel {

    private var artists: LiveData<List<Artist>> = MutableLiveData()

    override fun search(name: String) {
       artists = artistRepository.getArtists(name)
    }

    override fun getArtists(): LiveData<List<Artist>> = artists

}