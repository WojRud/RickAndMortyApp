package com.example.rickandmortyapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import java.time.chrono.HijrahChronology
import java.time.chrono.HijrahChronology.INSTANCE

@Database(entities = [FavoriteCharacterModel::class],
    version = 1,
    exportSchema = false)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun characterDao() : CharacterDao

    companion object {
        @Volatile
        private var INSTANCE: CharacterDatabase? = null

        fun getDatabase(context: Context): CharacterDatabase {
            return INSTANCE ?: kotlin.synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    CharacterDatabase::class.java,
                    "favorite_database"
                )
                    .createFromAsset("database/bus_schedule.db")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }


}