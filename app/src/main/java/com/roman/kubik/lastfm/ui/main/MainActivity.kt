package com.roman.kubik.lastfm.ui.main

import android.os.Bundle
import com.roman.kubik.lastfm.R
import com.roman.kubik.lastfm.ui.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


}