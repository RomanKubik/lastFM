package com.roman.kubik.lastfm.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.roman.kubik.lastfm.R
import com.roman.kubik.lastfm.repository.model.Artist
import kotlinx.android.synthetic.main.item_artist.view.*

class ArtistsAdapter(private val callback: ArtistAdapterCallback) :
    PagedListAdapter<Artist, ArtistsAdapter.ArtistsHolder>(
        object : DiffUtil.ItemCallback<Artist>() {
            override fun areItemsTheSame(oldItem: Artist, newItem: Artist) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Artist, newItem: Artist) = oldItem == newItem
        }
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_artist, parent, false)
        return ArtistsHolder(view, callback)
    }

    override fun onBindViewHolder(holder: ArtistsHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ArtistsHolder(itemView: View, private val callback: ArtistAdapterCallback) :
        RecyclerView.ViewHolder(itemView) {

        private var artist: Artist? = null

        init {
            itemView.setOnClickListener {
                artist?.let {
                    callback.onArtistSelected(it)
                }
            }
        }

        fun bind(artist: Artist?) {
            this.artist = artist
            if (artist != null) {
                Glide.with(itemView)
                    .load(artist.imagePath)
                    .error(R.drawable.ic_music_note)
                    .placeholder(R.drawable.ic_music_note)
                    .circleCrop()
                    .into(itemView.artistAvatar)
                itemView.artistName.text = artist.name
            } else {
                Glide.with(itemView)
                    .load(R.drawable.ic_music_note)
                    .fitCenter()
                    .into(itemView.artistAvatar)
                itemView.artistName.text = itemView.context.getText(R.string.loading)
            }
        }
    }

}