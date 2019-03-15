package com.roman.kubik.lastfm.repository.model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

/**
 * Data class that is necessary for a UI to show a listing and interact w/ the rest of the system
 */
data class Listing<T>(
    /** The LiveData of paged lists for the UI to observe. */
    val pagedList: LiveData<PagedList<T>>,
    /** Represents the network request status to show to the user. */
    val networkState: LiveData<NetworkState>
)