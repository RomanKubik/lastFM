package com.roman.kubik.lastfm.api.model

import com.google.gson.annotations.SerializedName

data class AlbumModel(
    @SerializedName("name") val name: String?,
    @SerializedName("mbid") val id: String?,
    @SerializedName("image") val images: List<Image> = ArrayList(),
    @SerializedName("tracks") val tracks: TracksModel?
)

data class TracksModel(
    @SerializedName("tracks") val tracks: ArrayList<TrackModel> = ArrayList()
)

data class TrackModel(
    @SerializedName("name") val name: String?
)