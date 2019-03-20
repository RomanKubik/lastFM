package com.roman.kubik.lastfm.persistence.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artist")
data class ArtistEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "imagePath") val imagePath: String?,
    @ColumnInfo(name = "imageUrl") val imageUrl: String?
)