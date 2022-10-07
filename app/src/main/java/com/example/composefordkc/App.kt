package com.example.composefordkc

import android.app.Application
import com.example.composefordkc.data.RickAndMortyApi
import com.example.composefordkc.data.RickAndMortyRepositoryImpl
import com.example.composefordkc.domain.RickAndMortyRepository

class App : Application() {
    companion object {
        private lateinit var apiService: RickAndMortyApi
        lateinit var rickAndMortyRepository: RickAndMortyRepository
            private set
    }

    override fun onCreate() {
        super.onCreate()
        initApiService()
        initRepository()
    }

    private fun initRepository() {
        rickAndMortyRepository = RickAndMortyRepositoryImpl(apiService)
    }

    private fun initApiService() {
        apiService = RickAndMortyApi.create()
    }
}