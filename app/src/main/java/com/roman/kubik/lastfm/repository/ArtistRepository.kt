package com.roman.kubik.lastfm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.roman.kubik.lastfm.api.LastFmRestService
import com.roman.kubik.lastfm.api.model.Artist
import com.roman.kubik.lastfm.api.model.ArtistResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class ArtistRepository @Inject constructor(private val restService: LastFmRestService) {

    fun getArtists(name: String): LiveData<List<Artist>> {
        val data = MutableLiveData<List<Artist>>()
        restService.searchArtists(name, 30, 0).enqueue(object : Callback<ArtistResponse> {

            override fun onResponse(call: Call<ArtistResponse>, response: Response<ArtistResponse>) {
                if (response.isSuccessful) {
                    data.value = response.body()?.results?.artistMatches?.artists
                }
            }

            override fun onFailure(call: Call<ArtistResponse>, t: Throwable) {
                data.value = ArrayList()
            }

        })
        return data
    }
}