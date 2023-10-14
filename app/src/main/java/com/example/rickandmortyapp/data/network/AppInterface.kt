package com.example.rickandmortyapp.data.network

import com.example.rickandmortyapp.data.network.CharacterModel
import retrofit2.http.GET
import retrofit2.http.Path

interface AppInterface {
    @GET("character/{ids}")
    suspend fun getCharactersByIds(@Path("ids") ids: String): List<CharacterModel>

    @GET("character/{id}")
    suspend fun getCharactersById(@Path("id") id: Int): CharacterModel
}