package com.skvortsovfk.detidedasongbook.domain.repository

import com.skvortsovfk.detidedasongbook.data.local.entity.Song
import kotlinx.coroutines.flow.Flow

interface SongRepository {
    val songs: Flow<List<Song>>

    fun getSongsByAlbumId(albumId: Int): Flow<List<Song>>

    fun getSong(songId: Int): Flow<Song>
}