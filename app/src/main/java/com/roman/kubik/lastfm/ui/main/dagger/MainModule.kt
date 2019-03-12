package com.roman.kubik.lastfm.ui.main.dagger

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.roman.kubik.lastfm.ui.main.MainViewModel
import dagger.Module
import dagger.Provides

@Module
abstract class MainModule {

    @Provides
    fun getViewModel(activity: AppCompatActivity) = ViewModelProviders.of(activity).get(MainViewModel::class.java)

}