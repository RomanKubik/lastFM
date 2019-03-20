package com.roman.kubik.lastfm.ui.search.dagger

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.roman.kubik.lastfm.ui.search.SearchFragment
import com.roman.kubik.lastfm.ui.search.SearchViewModel
import com.roman.kubik.lastfm.ui.search.SearchViewModelImpl
import dagger.Module
import dagger.Provides

@Module
object SearchModule {

    @JvmStatic
    @Provides
    fun getSearchViewModel(fragment: SearchFragment, factory: ViewModelProvider.Factory): SearchViewModel =
        ViewModelProviders.of(fragment, factory).get(SearchViewModelImpl::class.java)

}