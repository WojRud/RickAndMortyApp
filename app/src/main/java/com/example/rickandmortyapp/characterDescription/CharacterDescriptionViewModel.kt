package com.example.rickandmortyapp.characterDescription

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.local.CharacterDatabase
import com.example.rickandmortyapp.data.network.CharacterModel
import com.example.rickandmortyapp.data.local.CharacterRepository
import com.example.rickandmortyapp.data.local.FavoriteCharacterModel
import com.example.rickandmortyapp.data.network.RickAndMortyApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterDescriptionViewModel(application: Application) : AndroidViewModel(application) {
    private val _characterData = MutableStateFlow<CharacterModel?>(null)
    val characterData: StateFlow<CharacterModel?>
    get() = _characterData

    private val repository: CharacterRepository

    fun setCharacterId(id: Int) {
        viewModelScope.launch {
            try {
                val result = RickAndMortyApi.retrofitService.getCharactersById(id)

                _characterData.value = result

            } catch (e: Exception) {
                Log.e("CharacterViewModel", "Request error: ${e.message}")
            }
        }
    }

    init {
        val characterDao = CharacterDatabase.getDatabase(application).characterDao()
        repository = CharacterRepository(characterDao)
    }

    fun addCharacter(character: FavoriteCharacterModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCharacter(character)
        }
    }
}