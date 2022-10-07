package com.example.composefordkc.presentation

import com.example.composefordkc.presentation.model.CharacterUi
import com.example.composefordkc.presentation.model.EpisodeUI

object MockData {
    val character =
        CharacterUi("https://content2.rozetka.com.ua/goods/images/big/255763872.jpg", "Rick", "Male", listOf())
    val caracters = listOf(character, character, character)
    val episode = EpisodeUI(airDate = "air Date", created = "created", episode = "episode", name = "name")
    val episodes = listOf(episode, episode, episode, episode, episode, episode)
}