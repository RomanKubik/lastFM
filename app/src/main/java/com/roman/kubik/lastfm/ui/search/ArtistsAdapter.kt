package com.roman.kubik.lastfm.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.roman.kubik.lastfm.R
import com.roman.kubik.lastfm.api.model.Artist
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_artist.view.*

class ArtistsAdapter : RecyclerView.Adapter<ArtistsAdapter.ArtistsHolder>() {

    private val list = ArrayList<Artist>()

    fun addArtists(artists: List<Artist>) {
        list.addAll(artists)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_artist, parent, false)
        return ArtistsHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ArtistsHolder, position: Int) {
        holder.bind(list[position])
    }


    class ArtistsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(artist: Artist) {
            Picasso.get().load(artist.images.first().url).fit().into(itemView.artistAvatar)
            itemView.artistName.text = artist.name
        }

    }
}