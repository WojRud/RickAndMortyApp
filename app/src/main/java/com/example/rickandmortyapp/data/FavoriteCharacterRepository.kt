package com.example.rickandmortyapp.data

import androidx.lifecycle.LiveData
import com.example.rickandmortyapp.favoriteCharacter.FavoriteCharacterAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow


class FavoriteCharacterRepository(private val characterDao: CharacterDao) {


    val readAllData: Flow<List<FavoriteCharacterModel>> = characterDao.readAllData()

     //  val readAllData: List<FavoriteCharacterModel> = characterDao.getAll()

    suspend fun addCharacter(character: FavoriteCharacterModel) {
        characterDao.addCharacter(character)
    }
}