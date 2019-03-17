package com.roman.kubik.lastfm.repository.model

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

@Suppress("DataClassPrivateConstructor")
data class NetworkState private constructor(
    val status: Status,
    val msg: String? = null) {
    companion object {
        val LOADED = NetworkState(Status.SUCCESS)
        val LOADING = NetworkState(Status.RUNNING)
        fun error(msg: String?) = NetworkState(Status.FAILED, msg)
    }
}

@Suppress("DataClassPrivateConstructor")
data class DatabaseState private constructor(
    val status: Status,
    val msg: String? = null) {
    companion object {
        val LOADED = DatabaseState(Status.SUCCESS)
        val LOADING = DatabaseState(Status.RUNNING)
        fun error(msg: String?) = DatabaseState(Status.FAILED, msg)
    }
}