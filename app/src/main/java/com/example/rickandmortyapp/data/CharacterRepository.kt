package com.example.rickandmortyapp.data

import androidx.lifecycle.LiveData
import com.example.rickandmortyapp.favoriteCharacter.FavoriteCharacterAdapter
import kotlinx.coroutines.flow.Flow


class CharacterRepository(private val characterDao: CharacterDao) {

  //  val readAllData: LiveData<List<FavoriteCharacterModel>> = characterDao.readAllData()

    val readAllData: Flow<List<FavoriteCharacterModel>> = characterDao.readAllData()



 //   val readAllData: List<FavoriteCharacterModel> = characterDao.getAll()

    suspend fun addCharacter(character: FavoriteCharacterModel) {
        characterDao.addCharacter(character)
    }

    suspend fun getCharacterById(id: Int): FavoriteCharacterModel? {
        return characterDao.getCharacterById(id)
    }
}