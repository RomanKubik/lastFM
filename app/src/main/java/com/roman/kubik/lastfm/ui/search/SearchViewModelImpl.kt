package com.roman.kubik.lastfm.ui.search

import androidx.lifecycle.ViewModel
import com.roman.kubik.lastfm.api.Foo
import javax.inject.Inject

class SearchViewModelImpl @Inject constructor(val foo: Foo): ViewModel(), SearchViewModel {

}