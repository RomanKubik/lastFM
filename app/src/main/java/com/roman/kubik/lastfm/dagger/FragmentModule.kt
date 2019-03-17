package com.roman.kubik.lastfm.dagger

import com.roman.kubik.lastfm.ui.albums.AlbumsFragment
import com.roman.kubik.lastfm.ui.albums.dagger.AlbumsModule
import com.roman.kubik.lastfm.ui.search.SearchFragment
import com.roman.kubik.lastfm.ui.search.dagger.SearchModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @ContributesAndroidInjector(modules = [SearchModule::class])
    fun searchFragmentInjector(): SearchFragment

    @ContributesAndroidInjector(modules = [AlbumsModule::class])
    fun albumsFragmentInjector(): AlbumsFragment
}