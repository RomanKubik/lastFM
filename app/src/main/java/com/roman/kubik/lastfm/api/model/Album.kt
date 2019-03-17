package com.roman.kubik.lastfm.api.model

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("name") val name: String?,
    @SerializedName("mbid") val id: String?,
    @SerializedName("image") val images: ArrayList<Image> = ArrayList()
)