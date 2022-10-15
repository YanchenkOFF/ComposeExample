package com.example.composefordkc.presentation.detail

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.composefordkc.domain.RickAndMortyRepository
import com.example.composefordkc.presentation.detail.view.BoxedLazyRowState
import com.example.composefordkc.presentation.detail.view.DetailState
import com.example.composefordkc.presentation.mappers.toUI
import com.example.composefordkc.presentation.model.CharacterUi
import com.example.composefordkc.presentation.model.EpisodeUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

data class CharacterScreenState(
    val characterDetail: DetailState = DetailState(),
    val boxedEpisodes: BoxedLazyRowState = BoxedLazyRowState()
)

class CharacterDetailViewModel(private val repository: RickAndMortyRepository) : ViewModel() {
    var uiState by mutableStateOf(CharacterScreenState())
        private set

    fun getCharacterDetail(character: CharacterUi) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val numbers = character.getEpisodeNumbers()
                val episodes: List<EpisodeUI> = repository.getEpisodes(numbers).map { it.toUI() }

                uiState = uiState.copy(
                    characterDetail = character.toDetailState(),
                    boxedEpisodes = BoxedLazyRowState(
                        boxList = episodes.map { it.toBoxItem() }
                    )
                )
            } catch (e: Throwable) {
                Log.e(javaClass.simpleName, "error", e)
            }
        }
    }
}


private fun CharacterUi.getEpisodeNumbers(): List<String> {
    return episode.map { it.split("/").last() }
}

private fun CharacterUi.toDetailState() = DetailState(
    title = name,
    description = gender,
    image = image
)

private fun EpisodeUI.toBoxItem() = BoxedLazyRowState.BoxItem(
    firstRow = created,
    secondRow = name,
    thirdRow = episode
)

@Suppress("UNCHECKED_CAST")
class CharacterDetailViewModelFactory(
    private val repository: RickAndMortyRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterDetailViewModel(repository) as T
    }
}