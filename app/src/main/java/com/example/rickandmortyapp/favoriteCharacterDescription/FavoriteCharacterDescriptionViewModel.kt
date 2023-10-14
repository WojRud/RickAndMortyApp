package com.example.rickandmortyapp.favoriteCharacterDescription

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyapp.data.local.CharacterDao
import com.example.rickandmortyapp.data.local.FavoriteCharacterModel
import kotlinx.coroutines.flow.Flow

class FavoriteCharacterDescriptionViewModel(private val characterDao: CharacterDao) : ViewModel() {
    fun getCharacterById(id: Int): Flow<FavoriteCharacterModel> {
        return characterDao.getCharacterById(id)
    }
}

class FavoriteCharacterDescriptionViewModelFactory(private val characterDao: CharacterDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteCharacterDescriptionViewModel::class.java)) {
            return FavoriteCharacterDescriptionViewModel(characterDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}







