package com.roman.kubik.lastfm.repository.model

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED,
    EMPTY
}

@Suppress("DataClassPrivateConstructor")
data class NetworkState private constructor(
    val status: Status,
    val msg: String? = null) {
    companion object {
        val LOADED = NetworkState(Status.SUCCESS)
        val LOADING = NetworkState(Status.RUNNING)
        fun error(msg: String?) = NetworkState(Status.FAILED, msg)
        val NO_DATA = NetworkState(Status.EMPTY)
    }
}