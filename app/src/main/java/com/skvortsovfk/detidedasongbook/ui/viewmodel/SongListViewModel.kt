package com.skvortsovfk.detidedasongbook.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.skvortsovfk.detidedasongbook.domain.repository.SongRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SongListViewModel @Inject constructor(
    songRepository: SongRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val albumId = savedStateHandle.get<Int>("albumId")
        ?: throw IllegalArgumentException("Album ID required")

    val songs = songRepository.getSongsByAlbumId(albumId)
}