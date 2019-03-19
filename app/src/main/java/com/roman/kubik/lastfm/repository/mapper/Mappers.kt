package com.roman.kubik.lastfm.repository.mapper

import com.roman.kubik.lastfm.api.model.AlbumModel
import com.roman.kubik.lastfm.api.model.ArtistModel
import com.roman.kubik.lastfm.api.model.TrackModel
import com.roman.kubik.lastfm.persistence.model.AlbumEntity
import com.roman.kubik.lastfm.persistence.model.ArtistEntity
import com.roman.kubik.lastfm.persistence.model.TrackEntity
import com.roman.kubik.lastfm.repository.model.Album
import com.roman.kubik.lastfm.repository.model.Artist
import com.roman.kubik.lastfm.repository.model.Track
import java.util.*
import kotlin.collections.ArrayList


fun TrackModel.toTrack(albumId: String): Track? {
    return if (name != null) {
        Track(UUID.randomUUID().toString(), name, duration ?: 0, albumId)
    } else {
        null
    }
}

fun AlbumModel.toAlbum(artist: Artist?): Album? {
    if (id != null && name != null) {
        val tracks: ArrayList<Track?> = ArrayList()
        if (this.tracks?.tracks != null) {
            for (t in this.tracks.tracks) {
                tracks.add(t.toTrack(this.id))
            }
        }
        return Album(
            name = name,
            id = id,
            imagePath = images.firstOrNull()?.url,
            artist = artist,
            isLiked = false,
            tracks = tracks.filterNotNull()
        )
    } else {
        return null
    }
}

fun AlbumModel.toAlbum() = this.toAlbum(null)

fun ArtistModel.toArtist(): Artist? {
    return if (id != null && name != null) {
        Artist(
            name = name,
            id = id,
            imagePath = this.images?.firstOrNull()?.url
        )
    } else {
        null
    }
}

fun ArtistModel.toArtist(albums: List<Album>): Artist? {
    return if (id != null && name != null) {
        Artist(
            name = name,
            id = id,
            imagePath = this.images?.firstOrNull()?.url,
            albumList = albums
        )
    } else {
        null
    }
}

fun TrackEntity.toTrack() = Track(id, name, duration, albumId)

fun AlbumEntity.toAlbum() = Album(name, id, imagePath, null, true)

fun ArtistEntity.toArtist() = Artist(name, id, imagePath)

fun Track.toTrackEntity() = TrackEntity(id, name, duration, albumId)

fun Album.toAlbumEntity() = AlbumEntity(id, name, imagePath, artist!!.id)

fun Artist.toArtistEntity() = ArtistEntity(id, name, imagePath)
