package com.roman.kubik.lastfm.dagger

import com.roman.kubik.lastfm.persistence.service.PersistenceService
import com.roman.kubik.lastfm.persistence.service.PersistenceServiceImpl
import com.roman.kubik.lastfm.repository.albums.AlbumsRepository
import com.roman.kubik.lastfm.repository.albums.AlbumsRepositoryImpl
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

    @Binds
    @Singleton
    fun getAlbumsRepository(albumsRepository: AlbumsRepositoryImpl): AlbumsRepository

    @Binds
    @Singleton
    fun getPersistanceService(persistenceService: PersistenceServiceImpl): PersistenceService
}