package com.roman.kubik.lastfm.ui.main.dagger

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.roman.kubik.lastfm.ui.main.MainViewModel
import com.roman.kubik.lastfm.ui.main.MainActivity
import com.roman.kubik.lastfm.ui.main.MainViewModelImpl
import dagger.Module
import dagger.Provides

@Module
object MainModule {

    @JvmStatic
    @Provides
    fun getMainViewModel(activity: MainActivity, factory: ViewModelProvider.Factory): MainViewModel =
        ViewModelProviders.of(activity, factory).get(MainViewModelImpl::class.java)

}