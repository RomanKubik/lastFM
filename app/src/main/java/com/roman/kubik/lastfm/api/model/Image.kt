package com.roman.kubik.lastfm.api.model

import androidx.annotation.StringDef
import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("#text") val url: String?,
    @Size
    @SerializedName("size") val size: String?
) {

    @Retention(AnnotationRetention.SOURCE)
    @StringDef(SMALL, MEDIUM, LARGE, EXTRA_LARGE, MEGA)
    annotation class Size

    companion object {
        const val SMALL = "small"
        const val MEDIUM = "medium"
        const val LARGE = "large"
        const val EXTRA_LARGE = "extralarge"
        const val MEGA = "mega"
    }
}


