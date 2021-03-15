package com.skvortsovfk.detidedasongbook.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialFadeThrough
import com.skvortsovfk.detidedasongbook.R
import com.skvortsovfk.detidedasongbook.data.local.entity.Album
import com.skvortsovfk.detidedasongbook.databinding.FragmentAlbumListBinding
import com.skvortsovfk.detidedasongbook.ui.adapter.AlbumAdapter
import com.skvortsovfk.detidedasongbook.ui.viewmodel.AlbumListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import timber.log.Timber

@AndroidEntryPoint
class AlbumListFragment : Fragment(), AlbumAdapter.AlbumAdapterListener {

    private var _binding: FragmentAlbumListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AlbumListViewModel by viewModels()

    private val adapter = AlbumAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate()")
        enterTransition = MaterialFadeThrough()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("onCreateView()")
        _binding = FragmentAlbumListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated()")
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        binding.albumList.adapter = adapter
        subscribeUi(adapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("onDestroyView()")

        // Set adapter to null to prevent leaking
        binding.albumList.adapter = null
        _binding = null
    }

    private fun subscribeUi(adapter: AlbumAdapter) {
        lifecycleScope.launchWhenCreated {
            viewModel.albums.collect { albums ->
                adapter.submitList(albums)
            }
        }
    }

    override fun onAlbumClicked(cardView: View, album: Album) {
        exitTransition = MaterialElevationScale(false)
        reenterTransition = MaterialElevationScale(true)
        val songListTransitionName = getString(R.string.song_list_transition_name)
        val extras = FragmentNavigatorExtras(cardView to songListTransitionName)
        val action = AlbumListFragmentDirections
            .actionAlbumListFragmentToSongListFragment(album.id, album.name)
        findNavController().navigate(action, extras)
    }
}