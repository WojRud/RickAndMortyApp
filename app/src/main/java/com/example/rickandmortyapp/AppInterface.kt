package com.example.rickandmortyapp

import retrofit2.Call
import retrofit2.http.GET

interface AppInterface {
    @GET("character/1")
    fun getCharacterById(): Call<CharacterModel>
}