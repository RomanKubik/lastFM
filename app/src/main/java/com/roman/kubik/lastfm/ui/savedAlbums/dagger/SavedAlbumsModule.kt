package com.roman.kubik.lastfm.ui.savedAlbums.dagger

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.roman.kubik.lastfm.ui.savedAlbums.SavedAlbumsFragment
import com.roman.kubik.lastfm.ui.savedAlbums.SavedAlbumsViewModel
import com.roman.kubik.lastfm.ui.savedAlbums.SavedAlbumsViewModelImpl
import dagger.Module
import dagger.Provides

@Module
object SavedAlbumsModule {

    @JvmStatic
    @Provides
    fun getSavedAlbumsiewModel(fragment: SavedAlbumsFragment, factory: ViewModelProvider.Factory): SavedAlbumsViewModel =
        ViewModelProviders.of(fragment, factory).get(SavedAlbumsViewModelImpl::class.java)

}