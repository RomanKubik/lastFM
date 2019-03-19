package com.roman.kubik.lastfm.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.roman.kubik.lastfm.persistence.LastFmDatabase.Companion.DATABASE_VERSION
import com.roman.kubik.lastfm.persistence.model.AlbumEntity
import com.roman.kubik.lastfm.persistence.model.ArtistEntity
import com.roman.kubik.lastfm.persistence.model.TrackEntity

@Database(entities = [AlbumEntity::class, ArtistEntity::class, TrackEntity::class], version = DATABASE_VERSION, exportSchema = false)
abstract class LastFmDatabase: RoomDatabase() {

    abstract fun albumDao(): AlbumDao

    abstract fun artistDao(): ArtistDao

    abstract fun trackDao(): TrackDao

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "LastFmDb"
    }
}