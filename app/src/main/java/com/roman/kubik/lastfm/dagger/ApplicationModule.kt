package com.roman.kubik.lastfm.dagger

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module


@Module
interface ApplicationModule {

    @Binds
    fun bindContext(application: Application): Context

}