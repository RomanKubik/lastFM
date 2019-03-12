package com.roman.kubik.lastfm.dagger

import com.roman.kubik.lastfm.ui.main.MainActivity
import com.roman.kubik.lastfm.ui.main.dagger.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import android.app.Application
import android.content.Context
import dagger.Binds



@Module
interface ApplicationModule {

    @Binds
    fun bindContext(application: Application): Context

    @ContributesAndroidInjector(modules = [MainModule::class])
    fun mainActivityInjector(): MainActivity

}