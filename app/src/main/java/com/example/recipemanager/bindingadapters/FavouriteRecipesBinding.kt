package com.example.recipemanager.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recipemanager.adapters.FavouriteRecipesAdapter
import com.example.recipemanager.data.database.entities.FavouritesEntity

class FavouriteRecipesBinding {
    companion object {

        @BindingAdapter("setVisibility","setData",requireAll = false)
        @JvmStatic
        fun setVisibility(view: View, favoritesEntity: List<FavouritesEntity>?, mAdapter: FavouriteRecipesAdapter?) {
            when (view) {
                is RecyclerView -> {
                    val dataCheck = favoritesEntity.isNullOrEmpty()
                    view.isInvisible = dataCheck
                    if(!dataCheck){
                        favoritesEntity?.let { mAdapter?.setData(it) }
                    }
                }
                else -> view.isVisible = favoritesEntity.isNullOrEmpty()
            }
        }
    }
}