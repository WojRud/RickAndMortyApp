package com.example.rickandmortyapp.characterDescription

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.CharacterDatabase
import com.example.rickandmortyapp.data.CharacterModel
import com.example.rickandmortyapp.data.CharacterRepository
import com.example.rickandmortyapp.data.FavoriteCharacterModel
import com.example.rickandmortyapp.data.RickAndMortyApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterDescriptionViewModel(application: Application) : AndroidViewModel(application) {
    private val _characterData = MutableLiveData<CharacterModel>()
    val characterData: LiveData<CharacterModel>
        get() = _characterData

 //   private val readAllData: LiveData<List<FavoriteCharacterModel>>          //  //////////////////////         LUB  CharacterModel
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
    //    readAllData = repository.readAllData
    }

    fun addCharacter(character: FavoriteCharacterModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCharacter(character)
        }
    }

}