package com.example.composefordkc.domain

import com.example.composefordkc.domain.model.Episode

interface RickAndMortyRepository {
    suspend fun getCharacters(): List<com.example.composefordkc.domain.model.Character>
    suspend fun getEpisodes(numbers: List<String>): List<Episode>
}