package com.example.rickandmortyapp

import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService: AppInterface = retrofit.create(AppInterface::class.java)

    fun getCharacterById(callback: Callback<CharacterModel>) {
        apiService.getCharacterById().enqueue(callback)
    }
}