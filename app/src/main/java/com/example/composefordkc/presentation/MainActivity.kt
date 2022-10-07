package com.example.composefordkc.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.composefordkc.R
import com.example.composefordkc.presentation.characters.CharactersFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.commit {
            replace(R.id.fragmentContainer, CharactersFragment())
        }
    }
}