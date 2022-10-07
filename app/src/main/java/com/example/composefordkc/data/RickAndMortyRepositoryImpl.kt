package com.example.composefordkc.data

import com.example.composefordkc.data.mappers.toDomain
import com.example.composefordkc.domain.RickAndMortyRepository
import com.example.composefordkc.domain.model.Character

class RickAndMortyRepositoryImpl(private val api: RickAndMortyApi) : RickAndMortyRepository {
    override suspend fun getCharacters(): List<Character> {
        api.getCharacters()
        return api.getCharacters().results.map { it.toDomain() }
    }
}
