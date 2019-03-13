package com.roman.kubik.lastfm.dagger.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.roman.kubik.lastfm.ui.main.MainViewModelImpl
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
    @Singleton
    abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory
}