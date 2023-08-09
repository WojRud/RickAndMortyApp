package com.example.rickandmortyapp

data class CharacterModel (
    val id: Int,
    val name: String,
    val status: String,
    val specties: String,
    val gender: String
)

data class Origin(val name: String)