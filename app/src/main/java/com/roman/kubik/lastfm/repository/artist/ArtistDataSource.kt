package com.roman.kubik.lastfm.repository.artist

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.roman.kubik.lastfm.api.LastFmRestService
import com.roman.kubik.lastfm.api.model.Artist
import com.roman.kubik.lastfm.api.model.ArtistResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class ArtistDataSource @Inject constructor(private val restService: LastFmRestService, private val query: String) :
    PageKeyedDataSource<Int, Artist>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Artist>) {
        val page = 1

        restService.searchArtists(query, 30, page).enqueue(object : Callback<ArtistResponse> {

            override fun onResponse(call: Call<ArtistResponse>, response: Response<ArtistResponse>) {
                if (response.isSuccessful) {
                    val result = response.body()?.results

                    if (result != null && result.artistMatches?.artists != null) {
                        callback.onResult(result.artistMatches.artists, 0, result.totalResults, null, page + 1)
                    } else {
                        onFailure(call, HttpException(response))
                    }
                }
            }

            override fun onFailure(call: Call<ArtistResponse>, t: Throwable) {
                Log.d("MyTag", "Error: " + t.message)
            }

        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Artist>) {
        val page = params.key

        restService.searchArtists(query, 30, page).enqueue(object : Callback<ArtistResponse> {

            override fun onResponse(call: Call<ArtistResponse>, response: Response<ArtistResponse>) {
                if (response.isSuccessful) {
                    val result = response.body()?.results

                    if (result != null && result.artistMatches?.artists != null) {
                        callback.onResult(result.artistMatches.artists, page + 1)
                    } else {
                        onFailure(call, HttpException(response))
                    }
                }
            }

            override fun onFailure(call: Call<ArtistResponse>, t: Throwable) {
                Log.d("MyTag", "Error: " + t.message)
            }

        })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Artist>) {
        // Not necessary as data never changes.
    }
}