package com.roman.kubik.lastfm.dagger

import android.content.Context
import com.roman.kubik.lastfm.LastFmApp
import dagger.Component
import javax.inject.Singleton
import dagger.BindsInstance

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: LastFmApp)
}