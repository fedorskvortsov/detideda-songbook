package com.skvortsovfk.detidedasongbook.data.di

import android.content.Context
import com.skvortsovfk.detidedasongbook.data.local.dao.AlbumDao
import com.skvortsovfk.detidedasongbook.data.local.db.AppDatabase
import com.skvortsovfk.detidedasongbook.data.local.dao.SongDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideSongDao(appDatabase: AppDatabase): SongDao {
        return appDatabase.songDao()
    }

    @Provides
    fun provideAlbumDao(appDatabase: AppDatabase): AlbumDao {
        return appDatabase.albumDao()
    }
}