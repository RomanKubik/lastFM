package com.roman.kubik.lastfm.repository.model

import android.os.Parcel
import android.os.Parcelable

data class Artist(
    val name: String,
    val id: String,
    val imagePath: String?,
    val albumList: List<Album> = ArrayList(),
    val imageUrl: String?
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
        albumList = parcel.readArrayList(Album::class.java.classLoader) as ArrayList<Album>,
        imageUrl = parcel.readString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(id)
        dest.writeString(imagePath)
        dest.writeList(albumList)
        dest.writeString(imageUrl)
    }

    override fun describeContents() = 0

    fun getImage(): String? = imagePath ?: imageUrl

}