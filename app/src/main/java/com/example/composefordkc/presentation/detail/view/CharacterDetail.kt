package com.example.composefordkc.presentation.detail.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.composefordkc.presentation.model.CharacterUi
import kotlinx.coroutines.flow.StateFlow

interface DetailViewModel {
    val state: StateFlow<CharacterUi>
}

class DetailViewModelImpl(private val _state: StateFlow<CharacterUi>) : DetailViewModel {
    override val state: StateFlow<CharacterUi>
        get() = _state
}

@Composable
fun Detail(viewModel: DetailViewModel) {
    val state = viewModel.state.collectAsState()
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(240.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .align(Alignment.CenterHorizontally), model = state.value.image, contentDescription = null
            )
            Text(
                text = state.value.name,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp),
                fontSize = 40.sp
            )
            Text(
                text = state.value.gender,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp),
                fontSize = 20.sp
            )
        }
    }
}
