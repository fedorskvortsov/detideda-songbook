package com.skvortsovfk.detidedasongbook.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "songs")
data class Song(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    val albumId: Int,
    val name: String,
    val number: Int,
    val key: String,
    val bpm: Int,
    val lyrics: String
)