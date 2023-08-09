package com.example.rickandmortyapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class CharacterViewModel(private val repository: CharRepository, application: Application) : AndroidViewModel(application) {
    private val _characterLiveData = MutableLiveData<CharacterModel>()
    val characterLiveData: LiveData<CharacterModel> get() = _characterLiveData

    fun fetchCharacter() {
        repository.getCharacterById(object : Callback<CharacterModel> {
            override fun onResponse(
                call: Call<CharacterModel>,
                response: Response<CharacterModel>
            ) {
                if (response.isSuccessful) {
                    val character = response.body()
                    character?.let {
                        _characterLiveData.postValue(it)
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


}