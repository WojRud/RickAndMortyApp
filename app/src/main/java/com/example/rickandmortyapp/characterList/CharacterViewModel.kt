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

/*
    init {
        getCharacterData()
    }

 */

    fun getCharacterData() {

        viewModelScope.launch {
            try {
                val listResult = RickAndMortyApi.retrofitService.getCharacterById()

                _characterList.postValue(listResult)

                //         Log.d("CharacterViewModel", "Name: ${listResult.name} delivered")

            } catch (e: Exception) {
                Log.e("CharacterViewModel", "Request error: ${e.message}")
            }
        }

    }

}













/*


    val flowersLiveData = dataSource.getFlowerList()

    fun insertFlower(name: String?, flowerDescription: String?) {
        if (name == null) {
            return
        }

        val newCharacter = CharacterModel(

        )

        dataSource.addFlower(newCharacter)
    }

    class CharactersListViewModelFactory(private val context: CharactersListFragment) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CharacterViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

 */







