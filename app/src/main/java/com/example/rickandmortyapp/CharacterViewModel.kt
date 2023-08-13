package com.example.rickandmortyapp

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterViewModel : ViewModel() {

    private val _status = MutableLiveData<CharacterModel>()

    val status: LiveData<CharacterModel> = _status

 //   init {
 //       getCharacterData()
 //   }

    fun getCharacterData() {
        viewModelScope.launch {
            try {
                val listResult = RickAndMortyApi.retrofitService.getCharacterById()

                Log.d("CharacterViewModel", "Name: ${listResult.name} delivered")

            } catch (e: Exception) {
                Log.e("CharacterViewModel", "Request error: ${e.message}")
            }
        }
    }


/*
        fun fetchCharacter() {
            repository.getCharacterById(object : Callback<CharacterModel> {
                override fun onResponse(call: Call<CharacterModel>, response: Response<CharacterModel>) {
                    if (response.isSuccessful) {
                        val character = response.body()
                        character?.let {
                            Log.i("CharacterViewModel", "Name: ${it.name}")
                        }
                    } else {
                        Log.i("CharacterViewModel", "Response not successful: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<CharacterModel>, t: Throwable) {
                    Log.i("CharacterViewModel", t.message ?: "Null")
                    Log.e("CharacterViewModel", "Request error: ${t.message}")
                }
            })
        }



 */
}

