package com.example.composefordkc.presentation.model

import com.example.composefordkc.Constants

data class EpisodeUI(
    val airDate: String,
    val created: String,
    val episode: String,
    val name: String,
) {
    companion object {
        val initState = EpisodeUI(
            airDate = Constants.EMPTY_STRING,
            created = Constants.EMPTY_STRING,
            episode = Constants.EMPTY_STRING,
            name = Constants.EMPTY_STRING
        )
    }
}