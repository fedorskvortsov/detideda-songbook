package com.skvortsovfk.detidedasongbook.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.skvortsovfk.detidedasongbook.data.local.entity.Song
import com.skvortsovfk.detidedasongbook.databinding.ListItemSongBinding

class SongAdapter(
    private val listener: SongAdapterListener
) : ListAdapter<Song, SongViewHolder>(SongDiffCallback) {

    interface SongAdapterListener {
        fun onSongClicked(cardView: View, song: Song)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(
            ListItemSongBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener
        )
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object SongDiffCallback : DiffUtil.ItemCallback<Song>() {

    override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
        return oldItem == newItem
    }
}