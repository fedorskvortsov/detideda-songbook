package com.skvortsovfk.detidedasongbook.data.repository

import com.skvortsovfk.detidedasongbook.data.local.source.LocalDataSource
import com.skvortsovfk.detidedasongbook.domain.repository.SongRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongRepositoryImpl @Inject constructor(
    private val dataSource: LocalDataSource
) : SongRepository {
    override val songs = dataSource.songs

    override fun getSongsByAlbumId(albumId: Int) = dataSource.getSongsByAlbumId(albumId)

    override fun getSong(songId: Int) = dataSource.getSong(songId)

}