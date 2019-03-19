package com.roman.kubik.lastfm.dagger.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.roman.kubik.lastfm.ui.albums.AlbumsViewModelImpl
import com.roman.kubik.lastfm.ui.details.AlbumDetailsViewModelImpl
import com.roman.kubik.lastfm.ui.main.MainViewModelImpl
import com.roman.kubik.lastfm.ui.search.SearchViewModelImpl
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModelImpl::class)
    abstract fun bindMainViewModel(viewModel: MainViewModelImpl): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModelImpl::class)
    abstract fun bindSearchViewModel(viewModel: SearchViewModelImpl): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AlbumsViewModelImpl::class)
    abstract fun bindAlbumsViewModel(viewModel: AlbumsViewModelImpl): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AlbumDetailsViewModelImpl::class)
    abstract fun bindAlbumDetailsViewModel(viewModel: AlbumDetailsViewModelImpl): ViewModel

    @Binds
    @Singleton
    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory
}