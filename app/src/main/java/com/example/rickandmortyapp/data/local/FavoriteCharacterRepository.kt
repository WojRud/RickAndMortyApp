package com.example.rickandmortyapp.data.local

import com.example.rickandmortyapp.data.local.CharacterDao
import com.example.rickandmortyapp.data.local.FavoriteCharacterModel
import kotlinx.coroutines.flow.Flow


class FavoriteCharacterRepository(private val characterDao: CharacterDao) {

    val readAllData: Flow<List<FavoriteCharacterModel>> = characterDao.readAllData()

    suspend fun addCharacter(character: FavoriteCharacterModel) {
        characterDao.addCharacter(character)
    }
}