package com.roman.kubik.lastfm.api.model

import com.google.gson.annotations.SerializedName

data class TopAlbumsResponse(
    @SerializedName("topalbums") val topAlbums: TopAlbums?
)

data class TopAlbums(
    @SerializedName("album") val albums: ArrayList<AlbumModel> = ArrayList(),
    @SerializedName("@attr") val attributes: Attributes?
)

data class Attributes (
    @SerializedName("total") val total: Int
)