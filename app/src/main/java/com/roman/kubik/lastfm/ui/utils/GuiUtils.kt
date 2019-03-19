package com.roman.kubik.lastfm.ui.utils

fun getHumanReadableDuration(duration: Long): String {
    val sec: Long = duration % 60
    val min: Long = (duration % 3600) / 60

    return String.format("%02d:%02d", min, sec)
}