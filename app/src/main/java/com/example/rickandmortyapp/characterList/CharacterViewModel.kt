package com.example.rickandmortyapp.characterList

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.rickandmortyapp.data.CharacterDatabase
import com.example.rickandmortyapp.data.CharacterModel
import com.example.rickandmortyapp.data.CharacterRepository
import com.example.rickandmortyapp.data.FavoriteCharacterModel
import com.example.rickandmortyapp.data.RickAndMortyApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterViewModel(application: Application) : AndroidViewModel(application) {
    private val _characterList = MutableLiveData<List<CharacterModel>>()
    val characterList: LiveData<List<CharacterModel>> = _characterList

    private val readAllData: LiveData<List<FavoriteCharacterModel>>
    private val repository: CharacterRepository

    fun getCharacterData() {

        viewModelScope.launch {
            try {
                val ids = (1..150).joinToString(",")
                val listResult = RickAndMortyApi.retrofitService.getCharactersByIds(ids)

                _characterList.postValue(listResult)

            } catch (e: Exception) {
                Log.e("CharacterViewModel", "Request error: ${e.message}")
            }
        }

    }

    init {
        val characterDao = CharacterDatabase.getDatabase(application).characterDao()
        repository = CharacterRepository(characterDao)
        readAllData = repository.readAllData
    }

    fun addCharacter(character: FavoriteCharacterModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCharacter(character)
        }
    }



}








