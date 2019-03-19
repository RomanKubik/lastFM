package com.roman.kubik.lastfm.persistence.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "album",
    foreignKeys = [ForeignKey(
        entity = ArtistEntity::class,
        parentColumns = ["id"],
        childColumns = ["artistId"],
        onDelete = CASCADE
    )]
)
data class AlbumEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "imagePath") val imagePath: String?,
    @ColumnInfo(name = "artistId") val artistId: String
)