package com.example.rickandmortyapp.network

data class CharacterModel (
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String
)

data class Origin(val name: String)