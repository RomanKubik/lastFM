package com.roman.kubik.lastfm.persistence.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "track", foreignKeys = [ForeignKey(entity = AlbumEntity::class, parentColumns = ["id"], childColumns = ["albumId"])])
data class TrackEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "albumId") val albumId: String
)