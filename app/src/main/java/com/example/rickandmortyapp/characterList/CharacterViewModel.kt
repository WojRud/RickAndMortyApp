package com.example.rickandmortyapp.characterList

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.rickandmortyapp.data.network.CharacterModel
import com.example.rickandmortyapp.data.network.RickAndMortyApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.stateIn

class CharacterViewModel(application: Application) : AndroidViewModel(application) {
    private val _characterList = MutableStateFlow<List<CharacterModel>>(emptyList())
    val characterList: StateFlow<List<CharacterModel>> = _characterList
        .stateIn(viewModelScope, SharingStarted.Lazily ,emptyList())

    fun getCharacterData() {
        viewModelScope.launch {
            try {
                val ids = (1..150).joinToString(",")
                val listResult = RickAndMortyApi.retrofitService.getCharactersByIds(ids)

                _characterList.value = listResult

            } catch (e: Exception) {
                Log.e("CharacterViewModel", "Request error: ${e.message}")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}











