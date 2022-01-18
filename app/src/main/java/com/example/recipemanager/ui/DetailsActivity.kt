package com.example.recipemanager.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.example.recipemanager.R
import com.example.recipemanager.adapters.PagerAdapter
import com.example.recipemanager.data.database.entities.FavouritesEntity
import com.example.recipemanager.databinding.ActivityDetailsBinding
import com.example.recipemanager.ui.ingredients.IngredientsFragment
import com.example.recipemanager.ui.instructions.InstructionsFragment
import com.example.recipemanager.ui.overview.OverviewFragment
import com.example.recipemanager.util.Constants
import com.example.recipemanager.viewmodels.MainViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import java.lang.reflect.InvocationTargetException

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailsBinding

    private val args by navArgs<DetailsActivityArgs>()
    private val mainViewModel : MainViewModel by viewModels()

    private var recipeSaved = false
    private var savedRecipeId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBar)
        binding.toolBar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragments = ArrayList<Fragment>()
        fragments.add(OverviewFragment())
        fragments.add(IngredientsFragment())
        fragments.add(InstructionsFragment())

        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("Ingredients")
        titles.add("Instructions")

        val resultBundle = Bundle()
        try {
            if (args.result != null) {
                resultBundle.putParcelable(Constants.RECIPE_RESULT_KEY, args.result)
            }
        } catch (e: InvocationTargetException) {
            e.cause?.printStackTrace();
        } catch (e: Exception) {
            e.printStackTrace();
        }


        val pagerAdapter = PagerAdapter(
            fragments,
            resultBundle,
            this
        )

        binding.viewPager.apply {
            adapter = pagerAdapter
        }

        TabLayoutMediator(binding.tabLayout,binding.viewPager){ tab, position ->
            tab.text = titles[position]
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        val menuItem = menu?.findItem(R.id.save_to_favourites_menu)
        checkSavedRecipes(menuItem!!)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home) {
            finish()
        } else if (item.itemId == R.id.save_to_favourites_menu && !recipeSaved) {
            saveToFavourites(item)
        } else if (item.itemId == R.id.save_to_favourites_menu && recipeSaved){
            removeFromFavourites(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun checkSavedRecipes(menuItem: MenuItem) {
        mainViewModel.readFavouriteRecipes.observe(this, { favouritesEntity ->
            try {
                for (savedRecipe in favouritesEntity){
                    if(savedRecipe.result.id == args.result.id){
                        changeMenuItemColour(menuItem,R.color.yellow)
                        savedRecipeId = savedRecipe.id
                        recipeSaved = true
                        break
                    } else{
                        changeMenuItemColour(menuItem,R.color.white)
                    }
                }
            }catch (e : java.lang.Exception){
                Log.d("DetailsActivity", e.message.toString())
            }
        })
    }

    private fun saveToFavourites(item: MenuItem) {
        val favouritesEntity =
            FavouritesEntity(
                0,
                args.result)
        mainViewModel.insertFavouriteRecipes(favouritesEntity)
        changeMenuItemColour(item,R.color.yellow)
        showSnackBar("Recipe Saved")
        recipeSaved = true
    }

    private fun removeFromFavourites(menuItem: MenuItem){
        val favouritesEntity =
            FavouritesEntity(
                savedRecipeId,
                args.result
            )
        mainViewModel.deleteFavouriteRecipes(favouritesEntity)
        changeMenuItemColour(menuItem,R.color.white)
        showSnackBar("Removed from Favourites")
        recipeSaved = false
    }

    private fun showSnackBar(message : String) {
        Snackbar.make(
            binding.detailsLayout,
            message,
            Snackbar.LENGTH_SHORT
        ).setAction("Okay") {}
            .show()
    }

    private fun changeMenuItemColour(item: MenuItem, colour: Int) {
        item.icon.setTint(ContextCompat.getColor(this,colour))
    }
}