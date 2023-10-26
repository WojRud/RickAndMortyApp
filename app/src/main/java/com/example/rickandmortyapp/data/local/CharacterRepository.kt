package com.example.rickandmortyapp.data.local

class CharacterRepository(private val characterDao: CharacterDao) {

    suspend fun addCharacter(character: FavoriteCharacterModel) {
        characterDao.addCharacter(character)
    }
}