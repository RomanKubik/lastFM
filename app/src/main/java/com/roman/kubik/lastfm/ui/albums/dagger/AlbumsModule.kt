package com.roman.kubik.lastfm.ui.albums.dagger

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.roman.kubik.lastfm.ui.albums.AlbumsFragment
import com.roman.kubik.lastfm.ui.albums.AlbumsViewModel
import com.roman.kubik.lastfm.ui.albums.AlbumsViewModelImpl
import dagger.Module
import dagger.Provides

@Module
object AlbumsModule {

    @JvmStatic
    @Provides
    fun getAlbumsViewModel(fragment: AlbumsFragment, factory: ViewModelProvider.Factory): AlbumsViewModel =
        ViewModelProviders.of(fragment, factory).get(AlbumsViewModelImpl::class.java)
}