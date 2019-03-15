package com.roman.kubik.lastfm.util

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import javax.inject.Inject

class MainThreadExecutor @Inject constructor(): Executor {

    private val mainHandler = Handler(Looper.getMainLooper())

    override fun execute(command: Runnable?) {
        mainHandler.post(command)
    }
}