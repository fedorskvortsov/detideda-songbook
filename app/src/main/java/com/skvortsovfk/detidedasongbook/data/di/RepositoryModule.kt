package com.skvortsovfk.detidedasongbook.data.di

import com.skvortsovfk.detidedasongbook.data.repository.AlbumRepositoryImpl
import com.skvortsovfk.detidedasongbook.data.repository.SongRepositoryImpl
import com.skvortsovfk.detidedasongbook.domain.repository.AlbumRepository
import com.skvortsovfk.detidedasongbook.domain.repository.SongRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAlbumRepository(
        albumRepositoryImpl: AlbumRepositoryImpl
    ): AlbumRepository

    @Binds
    abstract fun bindSongRepository(
        songRepositoryImpl: SongRepositoryImpl
    ): SongRepository
}