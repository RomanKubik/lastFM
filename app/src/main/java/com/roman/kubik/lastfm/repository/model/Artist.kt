package com.roman.kubik.lastfm.repository.model

import android.os.Parcel
import android.os.Parcelable

data class Artist(
    val name: String,
    val id: String,
    val imagePath: String?,
    val albumList: List<Album> = ArrayList()
) : Parcelable {

    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<Artist> {
            override fun createFromParcel(parcel: Parcel) = Artist(parcel)
            override fun newArray(size: Int) = arrayOfNulls<Artist>(size)
        }
    }

    private constructor(parcel: Parcel) : this(
        name = parcel.readString()!!,
        id = parcel.readString()!!,
        imagePath = parcel.readString(),
        albumList = parcel.readArrayList(Album::class.java.classLoader) as ArrayList<Album>
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(id)
        dest.writeString(imagePath)
        dest.writeList(albumList)
    }

    override fun describeContents() = 0
}