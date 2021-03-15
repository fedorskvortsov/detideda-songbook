package com.skvortsovfk.detidedasongbook.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.skvortsovfk.detidedasongbook.data.local.entity.Album
import com.skvortsovfk.detidedasongbook.databinding.ListItemAlbumBinding

class AlbumAdapter(
    private val listener: AlbumAdapterListener
) : ListAdapter<Album, AlbumViewHolder>(AlbumDiffCallback) {

    interface AlbumAdapterListener {
        fun onAlbumClicked(cardView: View, album: Album)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(
            ListItemAlbumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener
        )
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object AlbumDiffCallback : DiffUtil.ItemCallback<Album>() {

    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }
}