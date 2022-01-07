package com.example.recipemanager.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.recipemanager.models.Result
import com.example.recipemanager.util.Constants.FAVOURITE_RECIPES_TABLE

@Entity(tableName = FAVOURITE_RECIPES_TABLE)
class FavouritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var result : Result
)
{
}