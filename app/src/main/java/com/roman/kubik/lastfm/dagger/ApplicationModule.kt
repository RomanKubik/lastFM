package com.roman.kubik.lastfm.dagger

import com.roman.kubik.lastfm.repository.artist.ArtistRepository
import com.roman.kubik.lastfm.repository.artist.ArtistRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface ApplicationModule {

    @Binds
    @Singleton
    fun getArtistRepository(artistRepositoryImpl: ArtistRepositoryImpl): ArtistRepository

}