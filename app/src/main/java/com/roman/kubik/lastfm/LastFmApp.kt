package com.roman.kubik.lastfm

import android.app.Activity
import android.app.Application
import com.roman.kubik.lastfm.dagger.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject



class LastFmApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent
            .builder()
            .context(this)
            .build()
            .inject(this)

    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector
}