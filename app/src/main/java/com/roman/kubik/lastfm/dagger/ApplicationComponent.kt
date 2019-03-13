package com.roman.kubik.lastfm.dagger

import android.content.Context
import com.roman.kubik.lastfm.LastFmApp
import com.roman.kubik.lastfm.dagger.viewModel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        FragmentModule::class]
)
interface ApplicationComponent : AndroidInjector<LastFmApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): ApplicationComponent
    }

}