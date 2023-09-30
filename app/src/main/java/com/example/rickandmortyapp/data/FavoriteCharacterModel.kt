package com.example.rickandmortyapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorite_table")
data class FavoriteCharacterModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "char_id") val id: Int,
    @ColumnInfo(name = "char_name") val name: String,
    @ColumnInfo(name = "char_status") val status: String,
    @ColumnInfo(name = "char_species") val species: String,
    @ColumnInfo(name = "char_gender") val gender: String,
    @ColumnInfo(name = "char_image") val image: String
)

