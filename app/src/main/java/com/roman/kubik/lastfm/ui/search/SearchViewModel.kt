package com.roman.kubik.lastfm.ui.search

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.roman.kubik.lastfm.api.model.Artist

interface SearchViewModel {

    fun search(name: String)

    fun getArtists(): LiveData<PagedList<Artist>>

}