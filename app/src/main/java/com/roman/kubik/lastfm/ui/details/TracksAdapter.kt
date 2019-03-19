package com.roman.kubik.lastfm.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.roman.kubik.lastfm.R
import com.roman.kubik.lastfm.repository.model.Track
import com.roman.kubik.lastfm.ui.utils.getHumanReadableDuration
import kotlinx.android.synthetic.main.item_track.view.*

class TracksAdapter : ListAdapter<Track, TracksAdapter.TrackViewHolder>(
    object : DiffUtil.ItemCallback<Track>() {
        override fun areItemsTheSame(oldItem: Track, newItem: Track) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Track, newItem: Track) = oldItem == newItem
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_track, parent, false)
        return TrackViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(track: Track, position: Int) {
            itemView.trackNumber.text = (position + 1).toString()
            itemView.trackName.text = track.name
            itemView.trackDuration.text = getHumanReadableDuration(track.duration)
        }
    }
}