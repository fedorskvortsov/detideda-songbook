package com.skvortsovfk.detidedasongbook.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.skvortsovfk.detidedasongbook.R
import com.skvortsovfk.detidedasongbook.data.local.entity.Song
import com.skvortsovfk.detidedasongbook.databinding.ListItemSongBinding

class SongViewHolder(
    private val binding: ListItemSongBinding,
    private val listener: SongAdapter.SongAdapterListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(song: Song) {
        binding.apply {
            cardView.apply {
                transitionName = context.getString(R.string.song_card_transition_name, song.id)
                setOnClickListener { view ->
                    listener.onSongClicked(view, song)
                }
            }
            binding.songNumber.text = song.number.toString()
            binding.songName.text = song.name
            binding.songBpm.text = binding.root.resources.getString(R.string.bpm, song.bpm)
            binding.songKey.text = song.key
        }
    }
}