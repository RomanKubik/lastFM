package com.roman.kubik.lastfm.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.roman.kubik.lastfm.persistence.model.Album
import com.roman.kubik.lastfm.persistence.model.Artist

@Dao
interface ArtistDao {

    @Query("SELECT * FROM artist WHERE id = :id")
    fun getArtistById(id: String): LiveData<Artist>

    @Insert
    fun insertArtist(artist: Artist)

    @Insert
    fun insertArtistAndAlbum(artist: Artist, album: Album)

    @Delete
    fun deleteArtist(artist: Artist)
}