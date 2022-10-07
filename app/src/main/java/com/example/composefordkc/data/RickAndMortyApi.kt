package com.example.composefordkc.data

import com.example.composefordkc.data.model.CharactersNW
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://rickandmortyapi.com/api/"

interface RickAndMortyApi {
    companion object {
        fun create(): RickAndMortyApi {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(RickAndMortyApi::class.java)
        }
    }

    @GET("character")
    suspend fun getCharacters(): CharactersNW
}