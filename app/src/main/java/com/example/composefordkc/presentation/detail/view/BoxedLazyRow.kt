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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class BoxedLazyRowState(
    val boxList: List<BoxItem> = emptyList()
) {
    data class BoxItem(
        val firstRow: String,
        val secondRow: String,
        val thirdRow: String
    )
}


@Composable
fun BoxedLazyRow(boxedLazyRowState: BoxedLazyRowState) {
    LazyRow(){
        items(items = boxedLazyRowState.boxList) {
            BoxItem(boxItem = it)
        }
    }
}

@Composable
fun BoxItem(boxItem: BoxedLazyRowState.BoxItem) {
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
                text = boxItem.firstRow,
                modifier = Modifier
                    .padding(bottom = 8.dp),
            )
            Text(
                text = boxItem.secondRow,
                modifier = Modifier
                    .padding(bottom = 8.dp),
            )
            Text(
                text = boxItem.thirdRow,
                modifier = Modifier
                    .padding(bottom = 8.dp),
            )
        }
    }
}
//
//@Composable
//@Preview
//fun PreviewEpisodeItem() {
//    BoxItem(state = MockData.episode)
//}