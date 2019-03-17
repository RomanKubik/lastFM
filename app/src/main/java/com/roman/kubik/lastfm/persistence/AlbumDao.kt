package com.roman.kubik.lastfm.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.roman.kubik.lastfm.persistence.model.Album

@Dao
interface AlbumDao {

    @Query("SELECT * FROM album")
    fun getAllAlbums(): LiveData<List<Album>>

    @Query("SELECT * FROM album WHERE artistId = :artistId")
    fun getArtistAlbum(artistId: String): LiveData<List<Album>>

    @Query("SELECT * FROM album WHERE id = :id")
    fun getAlbumById(id: String): LiveData<Album>

    @Insert
    fun insertAlbum(album: Album)

    @Delete
    fun deleteAlbum(album: Album)
}