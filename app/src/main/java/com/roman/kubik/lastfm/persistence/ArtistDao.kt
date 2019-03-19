package com.roman.kubik.lastfm.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import com.roman.kubik.lastfm.persistence.model.AlbumEntity
import com.roman.kubik.lastfm.persistence.model.ArtistEntity
import com.roman.kubik.lastfm.persistence.model.TrackEntity

@Dao
interface ArtistDao {

    @Query("SELECT * FROM artist WHERE id = :id")
    fun getArtistById(id: String): LiveData<ArtistEntity>

    @Query("SELECT * FROM artist WHERE id = :id")
    fun getArtistByIdSync(id: String): ArtistEntity

    @Insert(onConflict = IGNORE)
    fun insertArtist(artist: ArtistEntity)

    @Insert(onConflict = IGNORE)
    fun insertArtistAlbumAndTracks(artist: ArtistEntity, album: AlbumEntity, tracks: List<TrackEntity>)

    @Delete
    fun deleteArtist(artist: ArtistEntity)

}