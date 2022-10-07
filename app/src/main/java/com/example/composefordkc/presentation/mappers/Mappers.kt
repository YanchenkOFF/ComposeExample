package com.example.composefordkc.presentation.mappers

import com.example.composefordkc.domain.model.Character
import com.example.composefordkc.presentation.model.CharacterUi


internal fun Character.toUI() =
    CharacterUi(
        image = image,
        name = name,
        gender = gender
    )