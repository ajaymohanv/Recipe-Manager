package com.example.recipemanager.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recipemanager.adapters.FavouriteRecipesAdapter
import com.example.recipemanager.data.database.entities.FavouritesEntity

class FavouriteRecipesBinding {
    companion object {

        @BindingAdapter("viewVisibility","setData",requireAll = false)
        @JvmStatic
        fun setDataAndViewVisibility(
            view: View,
            favouritesEntity: List<FavouritesEntity>?,
            mapAdapter: FavouriteRecipesAdapter?
        ) {
            if(favouritesEntity.isNullOrEmpty()){
                when(view){
                    is ImageView ->{
                        view.visibility = View.VISIBLE
                    }
                    is TextView ->{
                        view.visibility = View.VISIBLE
                    }
                    is RecyclerView ->{
                        view.visibility = View.INVISIBLE
                    }
                }
            } else{
                when(view){
                    is ImageView ->{
                        view.visibility = View.INVISIBLE
                    }
                    is TextView ->{
                        view.visibility = View.INVISIBLE
                    }
                    is RecyclerView ->{
                        view.visibility = View.VISIBLE
                        mapAdapter?.setData(favouritesEntity)
                    }
                }
            }
        }
    }
}