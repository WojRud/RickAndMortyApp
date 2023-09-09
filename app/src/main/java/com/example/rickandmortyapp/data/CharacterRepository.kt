package com.example.rickandmortyapp.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow


class CharacterRepository(private val characterDao: CharacterDao) {

    val readAllData: LiveData<List<FavoriteCharacterModel>> = characterDao.readAllData()

 //   val readAllData: List<FavoriteCharacterModel> = characterDao.getAll()

    suspend fun addCharacter(character: FavoriteCharacterModel) {
        characterDao.addCharacter(character)
    }

















}