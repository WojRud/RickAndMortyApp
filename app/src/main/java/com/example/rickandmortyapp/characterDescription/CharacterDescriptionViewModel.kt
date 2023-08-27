package com.example.rickandmortyapp.characterDescription

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.CharacterModel
import com.example.rickandmortyapp.data.RickAndMortyApi
import kotlinx.coroutines.launch

class CharacterDescriptionViewModel : ViewModel() {
    private val _characterData = MutableLiveData<CharacterModel>()
    val characterData: LiveData<CharacterModel>
        get() = _characterData

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

}