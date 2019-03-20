package com.roman.kubik.lastfm.dagger

import com.roman.kubik.lastfm.ui.albums.AlbumsFragment
import com.roman.kubik.lastfm.ui.albums.dagger.AlbumsModule
import com.roman.kubik.lastfm.ui.details.AlbumDetailsFragment
import com.roman.kubik.lastfm.ui.details.dagger.AlbumDetailsModule
import com.roman.kubik.lastfm.ui.savedAlbums.SavedAlbumsFragment
import com.roman.kubik.lastfm.ui.savedAlbums.dagger.SavedAlbumsModule
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

    @ContributesAndroidInjector(modules = [AlbumDetailsModule::class])
    fun albumDetailsFragmentInjector(): AlbumDetailsFragment

    @ContributesAndroidInjector(modules = [SavedAlbumsModule::class])
    fun savedAlbumsFragmentInjector(): SavedAlbumsFragment
}