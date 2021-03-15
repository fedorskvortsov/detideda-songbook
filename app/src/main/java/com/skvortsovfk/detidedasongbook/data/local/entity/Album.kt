package com.skvortsovfk.detidedasongbook.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class Album(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    val name: String,
    val year: Int,
    val imageUrl: String
)