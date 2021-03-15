package com.skvortsovfk.detidedasongbook.ui.adapter

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.skvortsovfk.detidedasongbook.R
import com.skvortsovfk.detidedasongbook.data.local.entity.Album
import com.skvortsovfk.detidedasongbook.databinding.ListItemAlbumBinding

class AlbumViewHolder(
    private val binding: ListItemAlbumBinding,
    private val listener: AlbumAdapter.AlbumAdapterListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(album: Album) {
        binding.apply {
            cardView.apply {
                transitionName = context.getString(R.string.album_card_transition_name, album.id)
                setOnClickListener { view ->
                    listener.onAlbumClicked(view, album)
                }
            }
            albumItemTitle.text = album.name
            bindImageFromUrl(binding.albumItemImage, album.imageUrl)
        }
    }

    private fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
        if (!imageUrl.isNullOrEmpty()) {
            Glide.with(view.context)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
        }
    }
}