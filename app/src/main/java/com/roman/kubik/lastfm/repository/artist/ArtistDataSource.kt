package com.roman.kubik.lastfm.repository.artist

import androidx.core.util.Consumer
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.roman.kubik.lastfm.api.LastFmRestService
import com.roman.kubik.lastfm.api.model.ArtistModel
import com.roman.kubik.lastfm.api.model.ArtistResponse
import com.roman.kubik.lastfm.api.model.Results
import com.roman.kubik.lastfm.repository.model.Artist
import com.roman.kubik.lastfm.repository.model.NetworkState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class ArtistDataSource constructor(private val restService: LastFmRestService, private val query: String) :
    PageKeyedDataSource<Int, Artist>() {

    private val networkData = MutableLiveData<NetworkState>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Artist>) {
        networkData.value = NetworkState.LOADING
        val page = 1

        loadArtists(page, Consumer {
            networkData.value = NetworkState.LOADED
            callback.onResult(it.artistMatches!!.artists.map(this::mapToRepositoryModel), 0, it.totalResults, null, page + 1)
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Artist>) {
        val page = params.key

        loadArtists(page, Consumer {
            callback.onResult(it.artistMatches!!.artists.map(this::mapToRepositoryModel), page + 1)
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Artist>) {
        // Not necessary as data never changes.
    }

    fun getNetworkState() = networkData

    private fun loadArtists(page: Int, consumer: Consumer<Results>) {
        restService.searchArtists(query, page).enqueue(object : Callback<ArtistResponse> {

            override fun onResponse(call: Call<ArtistResponse>, response: Response<ArtistResponse>) {
                if (response.isSuccessful) {
                    val result = response.body()?.results

                    if (result != null && result.artistMatches?.artists != null) {
                        consumer.accept(result)
                    } else {
                        onFailure(call, HttpException(response))
                    }
                } else {
                    onFailure(call, HttpException(response))
                }
            }

            override fun onFailure(call: Call<ArtistResponse>, t: Throwable) {
                networkData.value = NetworkState.error(t.message)
            }

        })
    }

    private fun mapToRepositoryModel(artist: ArtistModel) = Artist(
        artist.name ?: "Unknown",
        artist.id ?: "Unknown",
        artist.images.firstOrNull()?.url
    )
}