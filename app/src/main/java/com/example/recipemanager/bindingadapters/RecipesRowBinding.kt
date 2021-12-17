package com.example.recipemanager.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import com.example.recipemanager.R

class RecipesRowBinding {

    companion object{

        @BindingAdapter("setNumberOfLikes")
        @JvmStatic
        fun setNumberOfLikes(textView : TextView, likes : Int){
            textView.text = likes.toString()
        }

        @BindingAdapter("setNumberOfMinutes")
        @JvmStatic
        fun setNumberOfMinutes(textView : TextView, minutes : Int){
            textView.text = minutes.toString()
        }

        @BindingAdapter("applyVeganColour")
        @JvmStatic
        fun applyVeganColour(view : View, vegan : Boolean){
            if(vegan){
                when (view) {
                    is TextView -> {
                        view.setTextColor(ContextCompat.getColor(view.context, R.color.green))
                    }
                    is ImageView -> {
                        view.setColorFilter(ContextCompat.getColor(view.context, R.color.green))
                    }
                }
            }
        }

        @BindingAdapter("loadImageFromUr")
        @JvmStatic
        fun loadImageFromUr(imageView: ImageView, url : String){
            imageView.load(url){
                crossfade(600)
            }
        }

    }


}