package com.roman.kubik.lastfm.repository.model

import android.os.Parcel
import android.os.Parcelable

data class Track(
    private val id: String,
    private val name: String,
    private val albumId: String
): Parcelable {

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<Track> {
            override fun createFromParcel(parcel: Parcel) = Track(parcel)
            override fun newArray(size: Int) = arrayOfNulls<Track>(size)
        }
    }

    private constructor(parcel: Parcel) : this(
        id = parcel.readString()!!,
        name = parcel.readString()!!,
        albumId = parcel.readString()!!
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(id)
        dest.writeString(name)
        dest.writeString(albumId)
    }

    override fun describeContents() = 0
}