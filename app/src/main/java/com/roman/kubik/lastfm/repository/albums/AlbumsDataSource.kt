package com.roman.kubik.lastfm.repository.albums

import androidx.core.util.Consumer
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.roman.kubik.lastfm.api.LastFmRestService
import com.roman.kubik.lastfm.api.model.AlbumModel
import com.roman.kubik.lastfm.api.model.TopAlbums
import com.roman.kubik.lastfm.api.model.TopAlbumsResponse
import com.roman.kubik.lastfm.repository.mapper.toAlbum
import com.roman.kubik.lastfm.repository.model.Album
import com.roman.kubik.lastfm.repository.model.Artist
import com.roman.kubik.lastfm.repository.model.NetworkState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response

class AlbumsDataSource constructor(private val restService: LastFmRestService, private val artist: Artist) :
    PageKeyedDataSource<Int, Album>() {

    private val networkData = MutableLiveData<NetworkState>()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Album>) {
        networkData.value = NetworkState.LOADING
        val page = 1

        loadTopAlbums(page, Consumer {
            networkData.value = if (it?.attributes?.total != 0) NetworkState.LOADED else NetworkState.NO_DATA
            callback.onResult(it.albums.map{a -> a.toAlbum(artist)}, 0, it.attributes!!.total, null, page + 1)
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Album>) {
        val page = params.key

        loadTopAlbums(page, Consumer {
            callback.onResult(it.albums.map{a -> a.toAlbum(artist)},  page + 1)
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Album>) {
        // Not necessary as data never changes.
    }


    fun getNetworkState() = networkData

    private fun loadTopAlbums(page: Int, consumer: Consumer<TopAlbums>) {
        restService.getTopAlbums(artist.id, page).enqueue(object : Callback<TopAlbumsResponse> {

            override fun onResponse(call: Call<TopAlbumsResponse>, response: Response<TopAlbumsResponse>) {
                if (response.isSuccessful) {
                    val result = response.body()?.topAlbums

                    if (result?.albums != null && result.attributes != null) {
                        consumer.accept(result)
                    } else {
                        onFailure(call, HttpException(response))
                    }
                } else {
                    onFailure(call, HttpException(response))
                }
            }

            override fun onFailure(call: Call<TopAlbumsResponse>, t: Throwable) {
                networkData.value = NetworkState.error(t.message)
            }

        })
    }

}