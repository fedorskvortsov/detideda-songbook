package com.skvortsovfk.detidedasongbook.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.skvortsovfk.detidedasongbook.data.local.entity.Album
import com.skvortsovfk.detidedasongbook.data.local.entity.Song
import com.skvortsovfk.detidedasongbook.data.local.dao.AlbumDao
import com.skvortsovfk.detidedasongbook.data.local.dao.SongDao

@Database(entities = [Song::class, Album::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao
    abstract fun albumDao(): AlbumDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .createFromAsset("database/detideda-songbook.db")
                .fallbackToDestructiveMigration()
                .build()
        }

        private const val DATABASE_NAME = "detideda-songbook-db"
    }
}