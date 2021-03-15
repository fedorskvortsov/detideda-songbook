package com.skvortsovfk.detidedasongbook.domain.repository

import com.skvortsovfk.detidedasongbook.data.local.entity.Album
import com.skvortsovfk.detidedasongbook.data.local.entity.AlbumWithSongs
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    val albums: Flow<List<Album>>

    val albumsWithSongs: Flow<List<AlbumWithSongs>>

    fun getAlbum(albumId: Int): Flow<Album>
}