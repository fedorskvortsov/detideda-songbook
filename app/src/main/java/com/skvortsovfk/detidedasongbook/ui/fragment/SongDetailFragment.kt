package com.skvortsovfk.detidedasongbook.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.transition.MaterialContainerTransform
import com.skvortsovfk.detidedasongbook.R
import com.skvortsovfk.detidedasongbook.databinding.FragmentSongDetailBinding
import com.skvortsovfk.detidedasongbook.domain.util.themeColor
import com.skvortsovfk.detidedasongbook.ui.viewmodel.SongDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import timber.log.Timber

@AndroidEntryPoint
class SongDetailFragment : Fragment() {

    private var _binding: FragmentSongDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SongDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate()")
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
        _binding = FragmentSongDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated()")
        subscribeUi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("onDestroyView()")
        _binding = null
    }

    private fun subscribeUi() {
        lifecycleScope.launchWhenCreated {
            viewModel.song.collect { song ->
                binding.lyrics.text = HtmlCompat.fromHtml(
                    song.lyrics,
                    HtmlCompat.FROM_HTML_MODE_COMPACT
                )
            }
        }
    }
}