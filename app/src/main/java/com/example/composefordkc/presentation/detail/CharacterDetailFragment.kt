package com.example.composefordkc.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.example.composefordkc.App
import com.example.composefordkc.R
import com.example.composefordkc.presentation.detail.view.Detail
import com.example.composefordkc.presentation.detail.view.EpisodeItems
import com.example.composefordkc.presentation.model.CharacterUi

class CharacterDetailFragment : Fragment() {
    private val viewModel: CharacterDetailViewModel by viewModels {
        CharacterDetailViewModelFactory(App.rickAndMortyRepository)
    }

    companion object {
        const val EXTRA_CHARACTER = "EXTRA_CHARACTER"
        const val EXTRA_DETAIL_KEY = "EXTRA_DETAIL_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false).apply {
            findViewById<ComposeView>(R.id.detailComposeView).setContent {
                Box() {
                    Column() {
                        Detail(viewModel = viewModel.detailViewModel)
                        EpisodeItems(viewModel = viewModel.episodeItemsViewModel)
                    }
                }
            }
        }
    }

    private fun setFragmentResultListener() {
        setFragmentResultListener(EXTRA_DETAIL_KEY) { _, bundle ->
            val character = bundle.getParcelable(EXTRA_CHARACTER) as? CharacterUi
            character?.let {
                viewModel.getCharacterDetail(character)
            }
        }
    }
}