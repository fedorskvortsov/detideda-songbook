package com.skvortsovfk.detidedasongbook.data.local.dao

import androidx.room.*
import com.skvortsovfk.detidedasongbook.data.local.entity.Album
import com.skvortsovfk.detidedasongbook.data.local.entity.AlbumWithSongs
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumDao {
    @Query("SELECT * FROM albums ORDER BY name")
    fun getAlbums(): Flow<List<Album>>

    @Query("SELECT * FROM albums WHERE id = :albumId")
    fun getAlbum(albumId: Int): Flow<Album>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(albums: List<Album>)

    @Transaction
    @Query("SELECT * FROM albums")
    fun getAlbumsWithSongs(): Flow<List<AlbumWithSongs>>
}