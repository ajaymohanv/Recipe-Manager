package com.example.recipemanager.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.example.recipemanager.R
import com.example.recipemanager.adapters.PagerAdapter
import com.example.recipemanager.ui.ingredients.IngredientsFragment
import com.example.recipemanager.ui.instructions.InstructionsFragment
import com.example.recipemanager.ui.overview.OverviewFragment
import com.example.recipemanager.util.Constants
import kotlinx.android.synthetic.main.activity_details.*
import java.lang.reflect.InvocationTargetException

class DetailsActivity : AppCompatActivity() {

    private val args by navArgs<DetailsActivityArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setSupportActionBar(toolBar)
        toolBar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
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
        } catch (e : InvocationTargetException) {
            e.cause?.printStackTrace();
        } catch (e : Exception) {
            e.printStackTrace();
        }


        val adapter = PagerAdapter(
            titles,
            fragments,
            resultBundle,
            supportFragmentManager
        )

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}