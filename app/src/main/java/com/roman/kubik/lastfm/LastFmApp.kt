package com.roman.kubik.lastfm

import com.roman.kubik.lastfm.dagger.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class LastFmApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent.builder().context(this).build()
}