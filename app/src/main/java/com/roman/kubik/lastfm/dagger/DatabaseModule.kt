package com.roman.kubik.lastfm.dagger

import android.content.Context
import androidx.room.Room
import com.roman.kubik.lastfm.persistence.LastFmDatabase
import com.roman.kubik.lastfm.persistence.LastFmDatabase.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun getAppDatabase(context: Context): LastFmDatabase =
        Room.databaseBuilder(context, LastFmDatabase::class.java, DATABASE_NAME)
            .build()

    @Provides
    @Singleton
    fun getArtistDao(lastFmDatabase: LastFmDatabase) = lastFmDatabase.artistDao()


    @Provides
    @Singleton
    fun getAlbumDao(lastFmDatabase: LastFmDatabase) = lastFmDatabase.albumDao()

    @Provides
    @Singleton
    fun getTrackDao(lastFmDatabase: LastFmDatabase) = lastFmDatabase.trackDao()
}