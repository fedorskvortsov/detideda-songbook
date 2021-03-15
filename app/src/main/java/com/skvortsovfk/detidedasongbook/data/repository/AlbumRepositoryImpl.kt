package com.skvortsovfk.detidedasongbook.data.repository

import com.skvortsovfk.detidedasongbook.data.local.source.LocalDataSource
import com.skvortsovfk.detidedasongbook.domain.repository.AlbumRepository
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val dataSource: LocalDataSource
) : AlbumRepository {
    override val albums = dataSource.albums

    override val albumsWithSongs = dataSource.albumsWithSongs

    override fun getAlbum(albumId: Int) = dataSource.getAlbum(albumId)

}