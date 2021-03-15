package com.skvortsovfk.detidedasongbook.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.skvortsovfk.detidedasongbook.data.repository.SongRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SongDetailViewModel @Inject constructor(
    songRepository: SongRepositoryImpl,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val songId = savedStateHandle.get<Int>("songId")
        ?: throw IllegalArgumentException("Song ID required")

    val song = songRepository.getSong(songId)
}