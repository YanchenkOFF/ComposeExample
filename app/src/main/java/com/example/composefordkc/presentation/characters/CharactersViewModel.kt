package com.example.composefordkc.presentation.characters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.composefordkc.domain.RickAndMortyRepository
import com.example.composefordkc.presentation.characters.view.CharacterViewModel
import com.example.composefordkc.presentation.mappers.toUI
import com.example.composefordkc.presentation.model.CharacterUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class CharactersViewAction {
    data class OpenCharacter(val item: CharacterUi) : CharactersViewAction()
}

class CharactersViewModel(private val repository: RickAndMortyRepository) : ViewModel(), CharacterViewModel {
    private val _state: MutableStateFlow<List<CharacterUi>> = MutableStateFlow(emptyList())
    override val state: StateFlow<List<CharacterUi>>
        get() = _state.asStateFlow()

    private val _action: MutableSharedFlow<CharactersViewAction> = MutableSharedFlow()
    val action: SharedFlow<CharactersViewAction> get() = _action.asSharedFlow()

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val characters = repository.getCharacters().map { it.toUI() }
                _state.emit(characters)
            } catch (e: Throwable) {
                Log.e(javaClass.simpleName, "error", e)
            }
        }
    }

    override fun onItemClick(item: CharacterUi) {
        viewModelScope.launch {
            _action.emit(CharactersViewAction.OpenCharacter(item))
        }
    }
}

@Suppress("UNCHECKED_CAST")
class CharactersViewModelFactory(
    private val repository: RickAndMortyRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharactersViewModel(repository) as T
    }
}