package com.example.rickandmortyapp.characterList

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.rickandmortyapp.data.CharacterModel
import com.example.rickandmortyapp.data.RickAndMortyApi
import kotlinx.coroutines.launch

class CharacterViewModel(application: Application) : AndroidViewModel(application) {
    private val _characterList = MutableLiveData<List<CharacterModel>>()
    val characterList: LiveData<List<CharacterModel>> = _characterList

    fun getCharacterData() {

        viewModelScope.launch {
            try {
                val ids = (1..150).joinToString(",")
                val listResult = RickAndMortyApi.retrofitService.getCharactersByIds(ids)

                _characterList.postValue(listResult)

                /*
                if (listResult.isNotEmpty()) {
                    _characterList.value = listResult.first()
                } else {
                    _characterList.value = null
                }
                 */

            } catch (e: Exception) {
                Log.e("CharacterViewModel", "Request error: ${e.message}")
            }
        }
    }
    override fun onCleared() {
        super.onCleared()
    }
}








