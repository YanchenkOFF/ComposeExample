package com.example.composefordkc.presentation.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.composefordkc.domain.RickAndMortyRepository
import com.example.composefordkc.presentation.detail.view.DetailViewModelImpl
import com.example.composefordkc.presentation.model.CharacterUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class DetailViewState(
    val character: CharacterUi
) {
    companion object {
        fun initState() = DetailViewState(
            character = CharacterUi.initState()
        )
    }
}

class DetailViewModel(private val repository: RickAndMortyRepository) : ViewModel() {
    private val _state: MutableStateFlow<DetailViewState> = MutableStateFlow(DetailViewState.initState())

    fun getCharacterDetail(character: CharacterUi) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.emit(_state.value.copy(character = character))
            } catch (e: Throwable) {
                Log.e(javaClass.simpleName, "error", e)
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(
    private val repository: RickAndMortyRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(repository) as T
    }
}