package com.roman.kubik.lastfm.api.model

import com.google.gson.annotations.SerializedName

data class AlbumInfoResponse(
    @SerializedName("album") val albumModel: AlbumModel?
)