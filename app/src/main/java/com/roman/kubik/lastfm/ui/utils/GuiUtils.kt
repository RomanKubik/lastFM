package com.roman.kubik.lastfm.ui.utils

import android.view.View
import android.view.ViewGroup

const val DEFAULT_DEBOUNCE_VIEW_TIMEOUT = 500L

fun getHumanReadableDuration(duration: Long): String {
    val sec: Long = duration % 60
    val min: Long = (duration % 3600) / 60

    return String.format("%02d:%02d", min, sec)
}

fun debounceView(view: View?) {
    debounceView(view, DEFAULT_DEBOUNCE_VIEW_TIMEOUT)
}

fun debounceView(view: View?, timeout: Long) {
    if (view is ViewGroup) {
        val viewGroup = view as ViewGroup?
        for (i in 0 until viewGroup!!.childCount) {
            debounceView(viewGroup.getChildAt(i), timeout)
        }
    }
    if (view != null) {
        view.isClickable = false
        view.postDelayed({ view.isClickable = true }, timeout)
    }
}