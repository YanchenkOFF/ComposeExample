package com.example.composefordkc.presentation.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.composefordkc.domain.RickAndMortyRepository
import com.example.composefordkc.fieldStateFlow
import com.example.composefordkc.presentation.detail.view.DetailViewModelImpl
import com.example.composefordkc.presentation.detail.view.EpisodeItemsViewModelImpl
import com.example.composefordkc.presentation.mappers.toUI
import com.example.composefordkc.presentation.model.CharacterUi
import com.example.composefordkc.presentation.model.EpisodeUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

data class CharacterDetailViewState(
    val character: CharacterUi,
    val episodes: List<EpisodeUI>
) {
    companion object {
        val initState = CharacterDetailViewState(
            character = CharacterUi.initState,
            episodes = emptyList()
        )
    }
}

class CharacterDetailViewModel(private val repository: RickAndMortyRepository) : ViewModel() {
    private val _state: MutableStateFlow<CharacterDetailViewState> =
        MutableStateFlow(CharacterDetailViewState.initState)

    val detailViewModel = DetailViewModelImpl(
        _state.fieldStateFlow(
            scope = viewModelScope,
            initState = CharacterUi.initState,
            transform = CharacterDetailViewState::character
        )
    )

    val episodeItemsViewModel = EpisodeItemsViewModelImpl(
        _state.fieldStateFlow(
            scope = viewModelScope,
            initState = emptyList(),
            transform = CharacterDetailViewState::episodes
        )
    )

    fun getCharacterDetail(character: CharacterUi) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.emit(_state.value.copy(character = character))
                val numbers = character.getEpisodeNumbers()
                val episodes: List<EpisodeUI> = repository.getEpisodes(numbers).map { it.toUI() }
                _state.emit(_state.value.copy(episodes = episodes))
            } catch (e: Throwable) {
                Log.e(javaClass.simpleName, "error", e)
            }
        }
    }
}


private fun CharacterUi.getEpisodeNumbers(): List<String> {
    return episode.map { it.split("/").last() }
}

@Suppress("UNCHECKED_CAST")
class CharacterDetailViewModelFactory(
    private val repository: RickAndMortyRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterDetailViewModel(repository) as T
    }
}