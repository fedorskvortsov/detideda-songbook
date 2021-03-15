package com.skvortsovfk.detidedasongbook.ui.fragment

import android.graphics.Color
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
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialFadeThrough
import com.skvortsovfk.detidedasongbook.R
import com.skvortsovfk.detidedasongbook.data.local.entity.Song
import com.skvortsovfk.detidedasongbook.databinding.FragmentSongListBinding
import com.skvortsovfk.detidedasongbook.domain.util.themeColor
import com.skvortsovfk.detidedasongbook.ui.adapter.SongAdapter
import com.skvortsovfk.detidedasongbook.ui.viewmodel.SongListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import timber.log.Timber

@AndroidEntryPoint
class SongListFragment : Fragment(), SongAdapter.SongAdapterListener {

    private var _binding: FragmentSongListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SongListViewModel by viewModels()

    private val adapter = SongAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate()")
        enterTransition = MaterialFadeThrough()
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.navHostFragment
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("onCreateView()")
        _binding = FragmentSongListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated()")
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        binding.songList.adapter = adapter
        binding.songList.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
        subscribeUi(adapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("onDestroyView()")

        // Set adapter to null to prevent leaking
        binding.songList.adapter = null
        _binding = null
    }

    private fun subscribeUi(adapter: SongAdapter) {
        lifecycleScope.launchWhenCreated {
            viewModel.songs.collect { songs ->
                adapter.submitList(songs)
            }
        }
    }

    override fun onSongClicked(cardView: View, song: Song) {
        exitTransition = MaterialElevationScale(false)
        reenterTransition = MaterialElevationScale(true)
        val songDetailTransitionName = getString(R.string.song_detail_transition_name)
        val extras = FragmentNavigatorExtras(cardView to songDetailTransitionName)
        val action = SongListFragmentDirections
            .actionSongListFragmentToSongDetailFragment(song.id, song.name)
        findNavController().navigate(action, extras)
    }
}