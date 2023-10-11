package com.example.rickandmortyapp.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.Flow


interface AppInterface {
    @GET("character/{ids}")
    suspend fun getCharactersByIds(@Path("ids") ids: String): List<CharacterModel>

    @GET("character/{id}")
    suspend fun getCharactersById(@Path("id") id: Int): CharacterModel

}