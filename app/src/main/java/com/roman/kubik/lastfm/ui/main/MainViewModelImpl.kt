package com.roman.kubik.lastfm.ui.main

import androidx.lifecycle.ViewModel
import com.roman.kubik.lastfm.api.Foo
import javax.inject.Inject

class MainViewModelImpl @Inject constructor(val foo: Foo) : ViewModel(), MainViewModel {
}