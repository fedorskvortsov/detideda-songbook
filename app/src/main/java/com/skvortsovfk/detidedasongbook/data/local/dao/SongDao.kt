package com.skvortsovfk.detidedasongbook.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.skvortsovfk.detidedasongbook.data.local.entity.Song
import kotlinx.coroutines.flow.Flow

@Dao
interface SongDao {
    @Query("SELECT * FROM songs ORDER BY name")
    fun getSongs(): Flow<List<Song>>

    @Query("SELECT * FROM songs WHERE albumId = :albumId")
    fun getSongsByAlbumId(albumId: Int): Flow<List<Song>>

    @Query("SELECT * FROM songs WHERE id = :songId")
    fun getSong(songId: Int): Flow<Song>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(songs: List<Song>)
}