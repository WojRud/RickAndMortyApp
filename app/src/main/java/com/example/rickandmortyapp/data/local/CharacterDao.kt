package com.example.rickandmortyapp.data.local

import android.app.Application
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Query("SELECT * FROM favorite_table ORDER BY char_id ASC")
    fun readAllData(): Flow<List<FavoriteCharacterModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCharacter(character: FavoriteCharacterModel)

    @Query("SELECT char_id, char_name, char_status, char_species, char_gender, char_image FROM favorite_table WHERE char_id = :id")
    fun getCharacterById(id: Int): Flow<FavoriteCharacterModel>

    @Query("DELETE FROM favorite_table WHERE char_id = :id")
    suspend fun deleteCharacterById(id: Int)
}

class FavoriteCharacterApplication : Application() {
    val database: CharacterDatabase by lazy { CharacterDatabase.getDatabase(this) }
}