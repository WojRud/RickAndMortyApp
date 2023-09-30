package com.example.rickandmortyapp.data

import android.app.Application
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl("https://rickandmortyapi.com/api/")
    .build()

interface AppInterface {
    @GET("character/{ids}")
    suspend fun getCharactersByIds(@Path("ids") ids: String): List<CharacterModel>

    @GET("character/{id}")
    suspend fun getCharactersById(@Path("id") id: Int): CharacterModel
}

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

object RickAndMortyApi {
    val retrofitService: AppInterface by lazy { retrofit.create(AppInterface::class.java) }
}

class FavoriteCharacterApplication : Application() {
    val database: CharacterDatabase by lazy { CharacterDatabase.getDatabase(this) }
}



