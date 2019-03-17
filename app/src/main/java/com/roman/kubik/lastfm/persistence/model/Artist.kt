package com.roman.kubik.lastfm.persistence.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artist")
data class Artist(
   @PrimaryKey val id: String,
   @ColumnInfo(name = "name") val name: String,
   @ColumnInfo(name = "imagePath") val imagePath: String?
)