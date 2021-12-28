package com.example.recipemanager.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.recipemanager.data.DataStoreRepository
import com.example.recipemanager.util.Constants
import com.example.recipemanager.util.Constants.API_KEY
import com.example.recipemanager.util.Constants.DEFAULT_DIET_TYPE
import com.example.recipemanager.util.Constants.DEFAULT_MEAL_TYPE
import com.example.recipemanager.util.Constants.QUERY_ADD_RECIPE_INFORMATION
import com.example.recipemanager.util.Constants.QUERY_API_KEY
import com.example.recipemanager.util.Constants.QUERY_DIET
import com.example.recipemanager.util.Constants.QUERY_FILL_INGREDIENTS
import com.example.recipemanager.util.Constants.QUERY_NUMBER
import com.example.recipemanager.util.Constants.QUERY_TYPE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RecipesViewModel @ViewModelInject constructor(
    application: Application,
    private val dataStoreRepository: DataStoreRepository
) : AndroidViewModel(application) {

    private var mealType = DEFAULT_MEAL_TYPE
    private var dietType = DEFAULT_DIET_TYPE

    var networkStatus = false
    var backOnline = false

    val readMealAndDietType = dataStoreRepository.readMealAndDietType
    val readBackOnline = dataStoreRepository.readBackOnline.asLiveData()

    fun saveBackOnline(backOnline : Boolean) {
        viewModelScope.launch {
            dataStoreRepository.saveBackOnline(backOnline)
        }
    }

    fun saveMealAndDietType(mealType: String, mealTypeId: Int, dietType: String, dietTypeId: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveMealAndDietType(mealType, mealTypeId, dietType, dietTypeId)
        }

     fun applyQueries() : HashMap<String,String>{
        val queries : HashMap<String,String> = HashMap()

         viewModelScope.launch {
             readMealAndDietType.collect {
                 mealType = it.selectedMealType
                 dietType = it.selectedDietType
             }
         }

        queries[QUERY_NUMBER] = Constants.DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = Constants.DEFAULT_MEAL_TYPE
        queries[QUERY_DIET] = Constants.DEFAULT_DIET_TYPE
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        return queries
    }

    fun showNetworkStatus() {
        if (!networkStatus) {
            Toast.makeText(getApplication(), "No Internet Connection.", Toast.LENGTH_SHORT).show()
            saveBackOnline(true)
        } else if (networkStatus) {
            if (backOnline) {
                Toast.makeText(getApplication(), "We're back online.", Toast.LENGTH_SHORT).show()
                saveBackOnline(false)
            }
        }
    }

}