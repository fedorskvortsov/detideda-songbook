package com.skvortsovfk.detidedasongbook.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.skvortsovfk.detidedasongbook.data.local.dao.SongDao
import com.skvortsovfk.detidedasongbook.data.local.db.AppDatabase
import com.skvortsovfk.detidedasongbook.data.local.entity.Song
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SongDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var songDao: SongDao

    private val albumId = 1

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
        "E",
        130,
        "Hello World!"
    )

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        songDao = database.songDao()

        songDao.insertAll(listOf(songA, songB, songC))
    }

    @After fun closeDb() {
        database.close()
    }

    @Test fun testGetSongs() = runBlocking {
        val songs = songDao.getSongs().first()
        Assert.assertThat(songs.size, equalTo(3))

        Assert.assertThat(songs[0], equalTo(songA))
        Assert.assertThat(songs[1], equalTo(songB))
        Assert.assertThat(songs[2], equalTo(songC))
    }

    @Test fun getSongsByAlbumId() = runBlocking {
        val songs = songDao.getSongsByAlbumId(albumId).first()
        Assert.assertThat(songs.size, equalTo(2))

        Assert.assertThat(songs[0], equalTo(songA))
        Assert.assertThat(songs[1], equalTo(songB))
    }

    @Test fun testGetSong() = runBlocking {
        Assert.assertThat(songDao.getSong(songA.id).first(), equalTo(songA))
    }
}