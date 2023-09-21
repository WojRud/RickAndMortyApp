package com.example.rickandmortyapp.favoriteCharacter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickandmortyapp.data.CharacterDao
import com.example.rickandmortyapp.data.FavoriteCharacterModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch


class FavoriteCharacterViewModel(private val characterDao: CharacterDao):ViewModel() {



    val readAllData: StateFlow<List<FavoriteCharacterModel>> =
        characterDao.readAllData().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

 //   fun readAllData(): StateFlow<List<FavoriteCharacterModel>> = characterDao.readAllData()            //////////////////////  A MOŻE TAK FLOW ZAMIAST LIVEDATA??



/*
    fun readAllData(): Flow<List<FavoriteCharacterModel>> {
        return characterDao.readAllData()
            .asFlow()
            .flowOn(Dispatchers.IO)
    }
 */



//    fun scheduleForStopName(name: String): Flow<List<FavoriteCharacterModel>> = characterDao.readAllData()

    class FavoriteCharacterViewModelFactory(
        private val characterDao: CharacterDao
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FavoriteCharacterViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return FavoriteCharacterViewModel(characterDao) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }






}

    /*


    class FavoriteCharacterViewModel(application: Application) : AndroidViewModel(application) {

    var readAllData: LiveData<List<FavoriteCharacterAdapter>>
        get() {
            TODO()
        }
    private var repository: CharacterRepository
        get() {
            TODO()
        }

    init {
        val characterDao = CharacterDatabase.getDatabase(application).characterDao()
        repository = FavoriteCharacterRepository(characterDao)
        readAllData = repository.readAllData
    }

    /*
        fun addCharacter(character: FavoriteCharacterModel) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.addCharacter(character)
            }
        }













        fun addCharacter(character: FavoriteCharacterModel) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.addCharacter(character)
            }
        }



     */


}




















/*

class FavoriteCharacterViewModel(private val itemDao: ItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemViewModel::class.java)) {
            return ItemViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}




























class FavoriteCharacterViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CharacterRepository

    val allItems: LiveData<List<ClipData.Item>>

    init {
        val itemDao = CharacterDatabase.getDatabase(application).itemDao()
        repository = ItemRepository(itemDao)
        allItems = repository.getAllItems()
    }




}







 */