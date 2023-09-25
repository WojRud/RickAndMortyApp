package com.example.rickandmortyapp.favoriteCharacterDescription

import androidx.lifecycle.ViewModel
import com.example.rickandmortyapp.data.CharacterRepository
import com.example.rickandmortyapp.data.FavoriteCharacterModel

class FavoriteCharacterDescriptionViewModel(private val repository: CharacterRepository) : ViewModel() {

    suspend fun getCharacterById(id: Int): FavoriteCharacterModel? {
        return repository.getCharacterById(id)
    }
}