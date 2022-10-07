package com.example.composefordkc.domain

interface RickAndMortyRepository {
    suspend fun getCharacters(): List<com.example.composefordkc.domain.model.Character>
}