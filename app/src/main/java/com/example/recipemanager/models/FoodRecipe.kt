package com.example.recipemanager.models

import com.example.recipemanager.models.Result
import com.google.gson.annotations.SerializedName

data class FoodRecipe(
    @SerializedName("results")
    val results: List<Result>
)