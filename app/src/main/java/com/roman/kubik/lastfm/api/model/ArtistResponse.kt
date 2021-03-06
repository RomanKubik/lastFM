package com.roman.kubik.lastfm.api.model

import com.google.gson.annotations.SerializedName

data class ArtistResponse(
    @SerializedName("results") val results: Results?
)

data class Results(
    @SerializedName("artistmatches") val artistMatches: Artists?,
    @SerializedName("opensearch:totalResults") val totalResults: Int
)

data class Artists(
    @SerializedName("artist") val artists: ArrayList<ArtistModel> = ArrayList()
)

data class ArtistModel(
    @SerializedName("name") val name: String?,
    @SerializedName("mbid") val id: String?,
    @SerializedName("image") val images: List<Image>? = ArrayList()
)