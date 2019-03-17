package com.roman.kubik.lastfm.persistence.model

import androidx.room.Embedded
import androidx.room.Relation

class DeepArtist {
    @Embedded
    lateinit var embedded: Artist
    @Relation(parentColumn = "id", entityColumn = "artistId", entity = Album::class) lateinit var deepAlbums: List<DeepAlbum>
}

class DeepAlbum {
    @Embedded lateinit var embedded: Album
    @Relation(parentColumn = "id", entityColumn = "albumId", entity = Track::class) lateinit var tracks: List<DeepTrack>
}

class DeepTrack {
    @Embedded lateinit var embedded: Track
}