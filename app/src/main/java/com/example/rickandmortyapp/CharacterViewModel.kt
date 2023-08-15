package com.example.rickandmortyapp

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterViewModel : ViewModel() {

 //   private val _status = MutableLiveData<CharacterModel>()

 //   val status: LiveData<CharacterModel> = _status

  //  private val _characterLiveData = MutableLiveData<CharacterModel>()

  //  val characterLiveData: LiveData<CharacterModel> get() = _characterLiveData


 //   init {
 //       getCharacterData()
 //   }

    fun getCharacterData() {

        viewModelScope.launch {
            try {
                val listResult = RickAndMortyApi.retrofitService.getCharacterById()

                Log.d("CharacterViewModel", "Name: ${listResult.name} delivered")
/*
                val characterInfo = CharacterModel(
                    id = listResult.id,
                    name = listResult.name,
                    status = listResult.status,
                    species = listResult.species,
                    gender = listResult.gender
                )
                _characterLiveData.postValue(characterInfo)
*/
            } catch (e: Exception) {
                Log.e("CharacterViewModel", "Request error: ${e.message}")
            }
        }

    }

}

