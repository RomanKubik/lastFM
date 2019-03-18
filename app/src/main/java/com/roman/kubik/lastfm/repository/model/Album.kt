package com.roman.kubik.lastfm.repository.model

import android.os.Parcel
import android.os.Parcelable

data class Album(
    val name: String,
    val id: String,
    val imagePath: String?,
    val isLiked: Boolean = false
): Parcelable {

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<Album> {
            override fun createFromParcel(parcel: Parcel) = Album(parcel)
            override fun newArray(size: Int) = arrayOfNulls<Album>(size)
        }
    }

    private constructor(parcel: Parcel) : this(
        name = parcel.readString()!!,
        id = parcel.readString()!!,
        imagePath = parcel.readString(),
        isLiked = parcel.readInt() != 0
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(id)
        dest.writeString(imagePath)
        dest.writeInt(if (isLiked) 1 else 0)
    }

    override fun describeContents() = 0

}