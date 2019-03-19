package com.roman.kubik.lastfm.ui.details.dagger

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.roman.kubik.lastfm.ui.details.AlbumDetailsFragment
import com.roman.kubik.lastfm.ui.details.AlbumDetailsViewModel
import com.roman.kubik.lastfm.ui.details.AlbumDetailsViewModelImpl
import dagger.Module
import dagger.Provides

@Module
object AlbumDetailsModule {

    @JvmStatic
    @Provides
    fun getAlbumDetailsViewModel(fragment: AlbumDetailsFragment, factory: ViewModelProvider.Factory): AlbumDetailsViewModel =
        ViewModelProviders.of(fragment, factory).get(AlbumDetailsViewModelImpl::class.java)
}