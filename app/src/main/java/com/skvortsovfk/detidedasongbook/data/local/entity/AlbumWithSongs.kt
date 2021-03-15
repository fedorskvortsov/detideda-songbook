package com.skvortsovfk.detidedasongbook.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity
data class AlbumWithSongs(
    @Embedded val album: Album,
    @Relation(parentColumn = "id", entityColumn = "albumId")
    val songs: List<Song>
)