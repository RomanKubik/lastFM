package com.roman.kubik.lastfm.dagger

import android.content.Context
import com.roman.kubik.lastfm.BuildConfig
import com.roman.kubik.lastfm.R
import com.roman.kubik.lastfm.api.LastFmRestService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun getHttpClient(context: Context): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.addInterceptor {
            var request = it.request()
            val url = request.url()
                .newBuilder()
                .addQueryParameter(PARAM_API_KEY, context.getString(R.string.last_fm_api_key))
                .addQueryParameter(PARAM_FORMAT, PARAM_FORMAT_VALUE)
                .build()
            request = request.newBuilder().url(url).build()
            it.proceed(request)
        }

        return builder.build()
    }

    @Provides
    @Singleton
    fun getRestService(client: OkHttpClient): LastFmRestService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.URL_API_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(LastFmRestService::class.java)
    }

    companion object {
        const val PARAM_API_KEY = "api_key"
        const val PARAM_FORMAT = "format"
        const val PARAM_FORMAT_VALUE = "json"
    }
}