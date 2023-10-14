package com.example.rickandmortyapp.favoriteCharacter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyapp.data.local.CharacterDao
import com.example.rickandmortyapp.data.local.FavoriteCharacterModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn


class FavoriteCharacterViewModel(private val characterDao: CharacterDao) : ViewModel() {

    val readAllData: StateFlow<List<FavoriteCharacterModel>> = characterDao.readAllData()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    class FavoriteCharacterViewModelFactory(
        private val characterDao: CharacterDao
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FavoriteCharacterViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FavoriteCharacterViewModel(characterDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}



