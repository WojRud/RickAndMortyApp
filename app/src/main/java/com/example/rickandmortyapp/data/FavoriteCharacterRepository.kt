package com.example.rickandmortyapp.data

import androidx.lifecycle.LiveData
import com.example.rickandmortyapp.favoriteCharacter.FavoriteCharacterAdapter

class FavoriteCharacterRepository(private val characterDao: CharacterDao) {


    val readAllData: LiveData<List<FavoriteCharacterModel>> = characterDao.readAllData()

    //   val readAllData: List<FavoriteCharacterModel> = characterDao.getAll()

    suspend fun addCharacter(character: FavoriteCharacterModel) {
        characterDao.addCharacter(character)
    }
}