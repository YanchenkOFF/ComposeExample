package com.example.composefordkc.presentation.characters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.composefordkc.App
import com.example.composefordkc.R
import com.example.composefordkc.presentation.characters.view.CharacterItems
import com.example.composefordkc.presentation.detail.DetailFragment
import com.example.composefordkc.presentation.model.CharacterUi
import kotlinx.coroutines.flow.collectLatest

class CharactersFragment : Fragment() {
    private val charactersViewModel by viewModels<CharactersViewModel> {
        CharactersViewModelFactory(App.rickAndMortyRepository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_characters, container, false).apply {
            findViewById<ComposeView>(R.id.composeView).setContent {
                Box() {
                    CharacterItems(charactersViewModel)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        Log.d("TAG", "SUBSCRIBE")
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            charactersViewModel.action.collectLatest { action ->
                when (action) {
                    is CharactersViewAction.OpenCharacter -> {
                        routeToDetailFragment(action.item)
                    }
                }
            }
        }
    }

    private fun routeToDetailFragment(item: CharacterUi) {
        val args = Bundle().apply {
            putParcelable(DetailFragment.EXTRA_CHARACTER, item)
        }
        setFragmentResult(DetailFragment.EXTRA_DETAIL_KEY, args)
        requireActivity().supportFragmentManager.commit {
            replace<DetailFragment>(R.id.fragmentContainer)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}
