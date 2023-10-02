package com.example.rickandmortyapp.data

import android.app.Application
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl("https://rickandmortyapi.com/api/")
    .build()

class FavoriteCharacterApplication : Application() {
    val database: CharacterDatabase by lazy { CharacterDatabase.getDatabase(this) }
}

object RickAndMortyApi {
    val retrofitService: AppInterface by lazy { retrofit.create(AppInterface::class.java) }
}



