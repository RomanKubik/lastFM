package com.roman.kubik.lastfm.api

import com.roman.kubik.lastfm.api.model.ArtistResponse
import com.roman.kubik.lastfm.api.model.TopAlbumsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFmRestService {

    companion object {
        const val DEFAULT_PAGE_SIZE = 30
    }

    @GET("2.0")
    fun searchArtists(@Query("artist") artist: String, @Query("page") page: Int,
                      @Query("limit") limit: Int = DEFAULT_PAGE_SIZE, @Query("method") method: String = "artist.search") : Call<ArtistResponse>

    @GET("2.0")
    fun getTopAlbums(@Query("mbid") artistId: String, @Query("page") page: Int,
                      @Query("limit") limit: Int = DEFAULT_PAGE_SIZE, @Query("method") method: String = "artist.gettopalbums") : Call<TopAlbumsResponse>

    @GET("2.0")
    fun getAlbumInfo(@Query("mbid") albumId: String, @Query("method") method: String = "album.getinfo")
}