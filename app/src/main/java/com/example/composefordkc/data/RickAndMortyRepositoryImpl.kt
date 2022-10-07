package com.example.composefordkc.data

import com.example.composefordkc.data.mappers.toDomain
import com.example.composefordkc.data.model.EpisodeNW
import com.example.composefordkc.domain.RickAndMortyRepository
import com.example.composefordkc.domain.model.Character
import com.example.composefordkc.domain.model.Episode

class RickAndMortyRepositoryImpl(private val api: RickAndMortyApi) : RickAndMortyRepository {
    override suspend fun getCharacters(): List<Character> {
        return api.getCharacters().results.map { it.toDomain() }
    }

    override suspend fun getEpisodes(numbers: List<String>): List<Episode> {
        return api.getEpisodes(numbers).map { it.toDomain() }
    }
}
