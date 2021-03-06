package com.example.recipemanager.data

import com.example.recipemanager.data.network.FoodRecipesApi
import com.example.recipemanager.models.FoodJoke
import com.example.recipemanager.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) {

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.getRecipes(queries)
    }

    suspend fun searchRecipes(searchQueries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.searchRecipes(searchQueries)
    }

    suspend fun getFoodJoke(apiKey: String) : Response<FoodJoke>{
        return foodRecipesApi.getFoodJoke(apiKey)
    }

}