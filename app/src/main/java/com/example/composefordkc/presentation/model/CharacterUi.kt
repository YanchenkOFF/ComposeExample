package com.example.composefordkc.presentation.model

import android.os.Parcelable
import com.example.composefordkc.Constants
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterUi(
    val image: String,
    val name: String,
    val gender: String,
    val episode: List<String>
) : Parcelable {
    companion object {
        val initState =
            CharacterUi(
                image = Constants.EMPTY_STRING,
                name = Constants.EMPTY_STRING,
                gender = Constants.EMPTY_STRING,
                episode = emptyList()
            )
    }
}