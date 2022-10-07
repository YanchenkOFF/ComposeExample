package com.example.composefordkc.data.mappers

import com.example.composefordkc.data.model.CharactersNW
import com.example.composefordkc.data.model.EpisodeNW
import com.example.composefordkc.domain.model.Character
import com.example.composefordkc.domain.model.Episode

internal fun CharactersNW.Result.toDomain(): Character = Character(
    episode = episode,
    gender = gender,
    image = image,
    name = name
)

internal fun EpisodeNW.toDomain() = Episode(
    airDate = airDate,
    characters = characters,
    created = created,
    episode = episode,
    id = id,
    name = name,
    url = url
)
