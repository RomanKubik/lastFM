package com.roman.kubik.lastfm.repository.mapper

import com.roman.kubik.lastfm.api.model.AlbumModel
import com.roman.kubik.lastfm.api.model.ArtistModel
import com.roman.kubik.lastfm.api.model.Image
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
    if (name != null) {
        val tracks: ArrayList<Track?> = ArrayList()
        if (this.tracks?.tracks != null) {
            for (t in this.tracks.tracks) {
                tracks.add(t.toTrack(id ?: name))
            }
        }
        return Album(
            name = name,
            id = id ?: name,
            imagePath = null,
            artist = artist,
            isLiked = false,
            tracks = tracks.filterNotNull(),
            imageUrl = getImageUrl(this.images)
        )
    } else {
        return null
    }
}

fun AlbumModel.toAlbum() = this.toAlbum(null)

fun ArtistModel.toArtist(): Artist? {
    return if (name != null) {
        Artist(
            name = name,
            id = id ?: name,
            imagePath = null,
            imageUrl = getImageUrl(this.images)
        )
    } else {
        null
    }
}

fun ArtistModel.toArtist(albums: List<Album>): Artist? {
    return if (name != null) {
        Artist(
            name = name,
            id = id ?: name,
            imagePath = null,
            albumList = albums,
            imageUrl = getImageUrl(this.images)
        )
    } else {
        null
    }
}

fun getImageUrl(images: List<Image>?): String? {
    return images?.find { it.size == Image.LARGE }?.url
        ?: images?.find { it.size == Image.MEDIUM }?.url
        ?: images?.find { it.size == Image.EXTRA_LARGE }?.url
        ?: images?.find { it.size == Image.SMALL }?.url
        ?: images?.find { it.size == Image.MEGA }?.url
}

fun TrackEntity.toTrack() = Track(id, name, duration, albumId)

fun AlbumEntity.toAlbum() = this.toAlbum(null)

fun AlbumEntity.toAlbum(artist: ArtistEntity?) = Album(name, id, imagePath, artist?.toArtist(), true, imageUrl = imageUrl)

fun ArtistEntity.toArtist() = Artist(name, id, imagePath, imageUrl = imageUrl)

fun Track.toTrackEntity() = TrackEntity(id, name, duration, albumId)

fun Album.toAlbumEntity() = this.toAlbumEntity(null)

fun Artist.toArtistEntity() = this.toArtistEntity(null)

fun Album.toAlbumEntity(imagePath: String?) = AlbumEntity(id, name, imagePath ?: this.imagePath, artist!!.id, imageUrl = imageUrl)

fun Artist.toArtistEntity(imagePath: String?) = ArtistEntity(id, name, imagePath ?: this.imagePath, imageUrl)
