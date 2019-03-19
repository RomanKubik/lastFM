package com.roman.kubik.lastfm.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.roman.kubik.lastfm.persistence.model.TrackEntity

@Dao
interface TrackDao {

    @Query("SELECT * FROM track WHERE albumId = :albumId")
    fun getAlbumTracks(albumId: String): LiveData<List<TrackEntity>>

    @Query("SELECT * FROM track WHERE albumId = :albumId")
    fun getAlbumTracksSync(albumId: String): List<TrackEntity>
}