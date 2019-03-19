package com.roman.kubik.lastfm.ui.savedAlbums

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.roman.kubik.lastfm.R
import com.roman.kubik.lastfm.repository.model.Album
import kotlinx.android.synthetic.main.item_saved_album.view.*

class SavedAlbumsAdapter(private val callback: SavedAlbumsAdapterCallback) :
    ListAdapter<Album, SavedAlbumsAdapter.SavedAlbumsViewHolder>(
        object : DiffUtil.ItemCallback<Album>() {
            override fun areItemsTheSame(oldItem: Album, newItem: Album) = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Album, newItem: Album) = oldItem == newItem
        }
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedAlbumsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_saved_album, parent, false)
        return SavedAlbumsViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: SavedAlbumsViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    class SavedAlbumsViewHolder(itemView: View, private val callback: SavedAlbumsAdapterCallback) :
        RecyclerView.ViewHolder(itemView) {

        private var album: Album? = null

        init {
            itemView.setOnClickListener {
                album?.let { a ->
                    callback.onAlbumSelected(a)
                }
            }
        }

        fun bindView(album: Album) {
            this.album = album
            itemView.savedArtistName.text = album.artist?.name
            itemView.savedAlbumName.text = album.name
            Glide.with(itemView)
                .load(album.imagePath)
                .error(R.drawable.ic_album)
                .placeholder(R.drawable.ic_album)
                .centerCrop()
                .into(itemView.savedAlbumImage)
        }
    }
}