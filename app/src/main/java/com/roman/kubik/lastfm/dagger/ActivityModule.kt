package com.roman.kubik.lastfm.dagger

import com.roman.kubik.lastfm.ui.main.MainActivity
import com.roman.kubik.lastfm.ui.main.dagger.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    fun mainActivityInjector(): MainActivity
}