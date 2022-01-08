package com.example.recipemanager.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recipemanager.data.database.entities.FavouritesEntity
import com.example.recipemanager.databinding.FavouriteRecipesRowLayoutBinding
import com.example.recipemanager.models.FoodRecipe
import com.example.recipemanager.util.RecipesDiffUtil

class FavouriteRecipesAdapter : RecyclerView.Adapter<FavouriteRecipesAdapter.MyViewHolder>() {

    private var favouriteRecipes = emptyList<FavouritesEntity>()
    class MyViewHolder(private val binding : FavouriteRecipesRowLayoutBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(favouritesEntity: FavouritesEntity){
            binding.favouritesEntity = favouritesEntity
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent: ViewGroup) : MyViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FavouriteRecipesRowLayoutBinding.inflate(layoutInflater,parent,false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val selectedRecipes = favouriteRecipes[position]
        holder.bind(selectedRecipes)
    }

    override fun getItemCount(): Int {
        return favouriteRecipes.size
    }

    fun setData(newFavouriteRecipes: List<FavouritesEntity>){
        val recipesDiffUtil =
            RecipesDiffUtil(favouriteRecipes, newFavouriteRecipes)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        favouriteRecipes = newFavouriteRecipes
        diffUtilResult.dispatchUpdatesTo(this)
    }


}