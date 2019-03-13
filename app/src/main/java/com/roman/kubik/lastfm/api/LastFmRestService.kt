package com.roman.kubik.lastfm.api

import com.roman.kubik.lastfm.api.model.ArtistResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFmRestService {

    @GET("2.0")
    fun searchArtists(@Query("artist") artist: String, @Query("limit") limit: Int,
                      @Query("page") page: Int, @Query("method") method: String = "artist.search") : Call<ArtistResponse>
}