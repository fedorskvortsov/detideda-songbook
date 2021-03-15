package com.skvortsovfk.detidedasongbook.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.skvortsovfk.detidedasongbook.domain.repository.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject constructor(
    albumRepository: AlbumRepository
) : ViewModel() {
    val albums = albumRepository.albums
}