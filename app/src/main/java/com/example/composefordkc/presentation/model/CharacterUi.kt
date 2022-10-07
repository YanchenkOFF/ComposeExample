package com.example.composefordkc.presentation.model

import android.os.Parcelable
import com.example.composefordkc.Constants
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterUi(
    val image: String, val name: String, val gender: String
) : Parcelable {
    companion object {
        fun initState(): CharacterUi =
            CharacterUi(
                image = Constants.EMPTY_STRING,
                name = Constants.EMPTY_STRING,
                gender = Constants.EMPTY_STRING
            )
    }
}