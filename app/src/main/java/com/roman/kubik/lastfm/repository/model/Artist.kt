package com.roman.kubik.lastfm.repository.model

data class Artist(
    val name: String,
    val id: String,
    val imagePath: String?,
    val albumList: ArrayList<Album> = ArrayList()
)