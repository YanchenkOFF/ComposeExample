package com.example.composefordkc.presentation.mappers

import com.example.composefordkc.domain.model.Character
import com.example.composefordkc.domain.model.Episode
import com.example.composefordkc.presentation.model.CharacterUi
import com.example.composefordkc.presentation.model.EpisodeUI

internal fun Character.toUI() =
    CharacterUi(
        image = image,
        name = name,
        gender = gender,
        episode = episode
    )

internal fun Episode.toUI() =
    EpisodeUI(
        airDate = airDate,
        created = created,
        episode = episode,
        name = name
    )
