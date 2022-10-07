package com.example.composefordkc.presentation.characters.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import coil.compose.AsyncImage
import com.example.composefordkc.data.MockData
import com.example.composefordkc.presentation.model.CharacterUi
import kotlinx.coroutines.flow.StateFlow

typealias OnCharacterClick = (CharacterUi) -> Unit

interface CharacterViewModel {
    val state: StateFlow<List<CharacterUi>>
    fun onItemClick(item: CharacterUi)
}

@Composable
fun CharacterItems(viewModel: CharacterViewModel) {
    val state = viewModel.state.collectAsState()
    LazyColumn() {
        items(items = state.value, itemContent = {
            CharacterItem(state = it, onItemClick = viewModel::onItemClick)
        })
    }
}

@Composable
fun CharacterItem(state: CharacterUi, onItemClick: OnCharacterClick) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(160.dp)
        .padding(all = 8.dp)
        .clip(shape = RoundedCornerShape(8.dp))
        .background(color = Color.Cyan)
        .clickable { onItemClick(state) }) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.Center)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(120.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .align(Alignment.CenterVertically), model = state.image, contentDescription = null
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
                    .padding(start = 16.dp)
            ) {
                Text(text = state.name)
                Text(text = state.gender)
            }
        }
    }
}

@Preview
@Composable
fun PreviewCharacterItem() {
    CharacterItem(MockData.character) {
    }
}
