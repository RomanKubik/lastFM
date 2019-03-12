package com.roman.kubik.lastfm.dagger

import com.roman.kubik.lastfm.ui.main.MainActivity
import com.roman.kubik.lastfm.ui.main.dagger.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [AndroidSupportInjectionModule::class])
interface ApplicationModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    fun mainActivityInjector(): MainActivity

}