package com.example.rickandmortyapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

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
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCharacter(character: FavoriteCharacterModel)

 //   @Query("SELECT * FROM favorite_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<FavoriteCharacterModel>>
}

object RickAndMortyApi {
    val retrofitService: AppInterface by lazy { retrofit.create(AppInterface::class.java) }
}



