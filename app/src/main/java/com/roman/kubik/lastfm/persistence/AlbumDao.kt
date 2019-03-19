package com.roman.kubik.lastfm.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.roman.kubik.lastfm.persistence.model.AlbumEntity

@Dao
interface AlbumDao {

    @Query("SELECT * FROM album")
    fun getAllAlbums(): LiveData<List<AlbumEntity>>

    @Query("SELECT * FROM album WHERE artistId = :artistId")
    fun getArtistAlbums(artistId: String): LiveData<List<AlbumEntity>>

    @Query("SELECT * FROM album WHERE id = :id")
    fun getAlbumById(id: String): LiveData<AlbumEntity>

    @Query("SELECT * FROM album WHERE artistId = :artistId")
    fun getArtistAlbumsSync(artistId: String): List<AlbumEntity>

    @Insert
    fun insertAlbum(album: AlbumEntity)

    @Delete
    fun deleteAlbum(album: AlbumEntity)
}