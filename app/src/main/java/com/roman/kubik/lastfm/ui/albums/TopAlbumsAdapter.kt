package com.roman.kubik.lastfm.ui.albums

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.roman.kubik.lastfm.R
import com.roman.kubik.lastfm.repository.model.Album
import kotlinx.android.synthetic.main.item_top_album.view.*

class TopAlbumsAdapter : PagedListAdapter<Album, TopAlbumsAdapter.TopAlbumsHolder>(
    object : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Album, newItem: Album) = oldItem == newItem
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopAlbumsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_top_album, parent, false)
        return TopAlbumsHolder(view)
    }

    override fun onBindViewHolder(holder: TopAlbumsHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TopAlbumsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(album: Album?) {
            if (album != null) {
                Glide.with(itemView)
                    .load(album.imagePath)
                    .error(R.drawable.ic_music_note)
                    .placeholder(R.drawable.ic_music_note)
                    .fitCenter()
                    .into(itemView.topAlbumImage)
                itemView.topAlbumName.text = album.name
            } else {
                Glide.with(itemView)
                    .load(R.drawable.ic_music_note)
                    .fitCenter()
                    .into(itemView.topAlbumImage)
                itemView.topAlbumName.text = itemView.context.getText(R.string.loading)
            }
        }
    }

}