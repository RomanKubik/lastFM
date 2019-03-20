package com.roman.kubik.lastfm.repository.model

import android.os.Parcel
import android.os.Parcelable

data class Album(
    val name: String,
    val id: String,
    val imagePath: String?,
    val artist: Artist?,
    val isLiked: Boolean = false,
    val tracks: List<Track> = ArrayList(),
    val imageUrl: String?
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
        artist = parcel.readParcelable(Artist::class.java.classLoader)!!,
        isLiked = parcel.readInt() != 0,
        tracks = parcel.readArrayList(Track::class.java.classLoader) as ArrayList<Track>,
        imageUrl = parcel.readString()
    )

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(id)
        dest.writeString(imagePath)
        dest.writeParcelable(artist, flags)
        dest.writeInt(if (isLiked) 1 else 0)
        dest.writeList(tracks)
        dest.writeString(imageUrl)
    }

    override fun describeContents() = 0

    fun getImage(): String? = imagePath ?: imageUrl

}