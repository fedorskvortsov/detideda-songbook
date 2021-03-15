package com.skvortsovfk.detidedasongbook.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.skvortsovfk.detidedasongbook.data.local.dao.AlbumDao
import com.skvortsovfk.detidedasongbook.data.local.db.AppDatabase
import com.skvortsovfk.detidedasongbook.data.local.entity.Album
import com.skvortsovfk.detidedasongbook.data.local.entity.Song
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var albumDao: AlbumDao

    private val albumA = Album(
        1,
        "A",
        2016,
        "a"
    )
    private val albumB = Album(
        2,
        "B",
        2016,
        "b"
    )
    private val albumC = Album(
        3,
        "C",
        2017,
        "c"
    )

    private val songA = Song(
        1,
        1,
        "A",
        1,
        "Em",
        130,
        "Hello World!"
    )
    private val songB = Song(
        2,
        1,
        "B",
        2,
        "Dm",
        130,
        "Hello World!"
    )
    private val songC = Song(
        3,
        2,
        "C",
        1,
        "A",
        130,
        "Hello World!"
    )

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        albumDao = database.albumDao()
        database.songDao().insertAll(listOf(songA, songB, songC))
        albumDao.insertAll(listOf(albumA, albumB, albumC))
    }

    @After fun closeDb() {
        database.close()
    }

    @Test fun testGetAlbums() = runBlocking {
        val albums = albumDao.getAlbums().first()
        assertThat(albums.size, equalTo(3))

        assertThat(albums[0], equalTo(albumA))
        assertThat(albums[1], equalTo(albumB))
        assertThat(albums[2], equalTo(albumC))
    }

    @Test fun testGetAlbum() = runBlocking {
        assertThat(albumDao.getAlbum(albumA.id).first(), equalTo(albumA))
    }

    @Test fun testGetAlbumWithSongs() = runBlocking {
        val albumWithSongs = albumDao.getAlbumsWithSongs().first()
        assertThat(albumWithSongs.size, equalTo(3))

        assertThat(albumWithSongs[0].album, equalTo(albumA))
        assertThat(albumWithSongs[0].songs.size, equalTo(2))
        assertThat(albumWithSongs[0].songs[0], equalTo(songA))
    }
}