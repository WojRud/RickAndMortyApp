package com.example.rickandmortyapp.characterList

import android.util.Log
import androidx.lifecycle.*
import com.example.rickandmortyapp.data.CharacterModel
import com.example.rickandmortyapp.data.RickAndMortyApi
import kotlinx.coroutines.launch
import javax.sql.DataSource

class CharacterViewModel : ViewModel() {
    private val _characterList = MutableLiveData<List<CharacterModel>>()
    val characterList: LiveData<List<CharacterModel>> = _characterList

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

}







