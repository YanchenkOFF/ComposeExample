package com.example.composefordkc.data.mappers

import com.example.composefordkc.data.model.CharactersNW
import com.example.composefordkc.domain.model.Character

internal fun CharactersNW.Result.toDomain(): Character = Character(
    episode = episode,
    gender = gender,
    image = image,
    name = name
)