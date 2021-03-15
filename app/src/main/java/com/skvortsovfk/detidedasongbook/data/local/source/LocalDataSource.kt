package com.skvortsovfk.detidedasongbook.data.local.source

import com.skvortsovfk.detidedasongbook.data.local.dao.AlbumDao
import com.skvortsovfk.detidedasongbook.data.local.dao.SongDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val albumDao: AlbumDao,
    private val songDao: SongDao
) {
    val albums = albumDao.getAlbums()

    val albumsWithSongs = albumDao.getAlbumsWithSongs()

    fun getAlbum(albumId: Int) = albumDao.getAlbum(albumId)

    val songs = songDao.getSongs()

    fun getSongsByAlbumId(albumId: Int) = songDao.getSongsByAlbumId(albumId)

    fun getSong(songId: Int) = songDao.getSong(songId)
}