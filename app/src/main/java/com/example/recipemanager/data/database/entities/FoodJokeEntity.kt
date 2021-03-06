package com.example.recipemanager.data.database.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.recipemanager.models.FoodJoke
import com.example.recipemanager.util.Constants.FOOD_JOKE_TABLE

@Entity(tableName = FOOD_JOKE_TABLE)
class FoodJokeEntity(
    @Embedded
    var foodJoke : FoodJoke
) {
    @PrimaryKey(autoGenerate = false)
    var id : Int = 0
}