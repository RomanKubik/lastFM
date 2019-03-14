package com.roman.kubik.lastfm.ui.search

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.roman.kubik.lastfm.R
import com.roman.kubik.lastfm.api.model.Artist
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_artist.view.*

class ArtistsAdapter : PagedListAdapter<Artist, ArtistsAdapter.ArtistsHolder>(
    object : DiffUtil.ItemCallback<Artist>() {
        override fun areItemsTheSame(oldItem: Artist, newItem: Artist) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Artist, newItem: Artist) = oldItem.equals(newItem)
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_artist, parent, false)
        return ArtistsHolder(view)
    }

    override fun onBindViewHolder(holder: ArtistsHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ArtistsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(artist: Artist?) {
            if (artist != null) {
                val url = artist.images.firstOrNull()?.url
                Picasso.get()
                    .load(if (TextUtils.isEmpty(url)) INCORRECT_URL else url)
                    .error(R.drawable.ic_music_note)
                    .fit()
                    .into(itemView.artistAvatar)
                itemView.artistName.text = artist.name
            }
        }

    }

    companion object {
        const val INCORRECT_URL = "dummy.com"
    }
}