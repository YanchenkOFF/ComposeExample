package com.example.composefordkc.presentation.detail.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composefordkc.presentation.MockData
import com.example.composefordkc.presentation.model.EpisodeUI
import kotlinx.coroutines.flow.StateFlow

interface EpisodeItemsViewModel {
    val state: StateFlow<List<EpisodeUI>>
}

class EpisodeItemsViewModelImpl(private val _state: StateFlow<List<EpisodeUI>>) : EpisodeItemsViewModel {
    override val state: StateFlow<List<EpisodeUI>>
        get() = _state
}

@Composable
fun EpisodeItems(viewModel: EpisodeItemsViewModel) {
    val state = viewModel.state.collectAsState()
    LazyRow(){
        items(items = state.value){
            EpisodeItem(state = it)
        }
    }
}

@Composable
fun EpisodeItem(state: EpisodeUI) {
    Box(
        modifier = Modifier
            .padding(all = 8.dp)
            .size(width = 150.dp, height = 100.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .background(Color.Gray)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = state.episode,
                modifier = Modifier
                    .padding(bottom = 8.dp),
            )
            Text(
                text = state.created,
                modifier = Modifier
                    .padding(bottom = 8.dp),
            )
            Text(
                text = state.airDate,
                modifier = Modifier
                    .padding(bottom = 8.dp),
            )
        }
    }
}

@Composable
@Preview
fun PreviewEpisodeItem() {
    EpisodeItem(state = MockData.episode)
}