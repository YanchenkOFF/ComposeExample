package com.example.composefordkc.presentation.detail.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

data class DetailState(
    val title: String = "",
    val description: String= "",
    val image: String= ""
)

@Composable
fun Detail(detailState: DetailState) {
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
                    .align(Alignment.CenterHorizontally),
                model = detailState.image,
                contentDescription = null
            )
            Text(
                text = detailState.title,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp),
                fontSize = 40.sp
            )
            Text(
                text = detailState.description,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp),
                fontSize = 20.sp
            )
        }
    }
}
