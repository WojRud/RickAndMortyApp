package com.example.rickandmortyapp.favoriteCharacter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.CharacterDatabase
import com.example.rickandmortyapp.data.CharacterRepository
import com.example.rickandmortyapp.data.FavoriteCharacterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteCharacterViewModel(application: Application) : AndroidViewModel(application) {
    private val readAllData: LiveData<List<FavoriteCharacterModel>>
    private val repository: CharacterRepository

    init {
        val characterDao = CharacterDatabase.getDatabase(application).CharacterDao()
        repository = CharacterRepository(characterDao)
        readAllData = repository.readAllData
    }

    fun addCharacter(character: FavoriteCharacterModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCharacter(character)
        }
    }










}