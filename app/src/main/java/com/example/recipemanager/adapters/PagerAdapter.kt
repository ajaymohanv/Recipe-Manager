package com.example.recipemanager.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(
    private val fragments : ArrayList<Fragment>,
    private val resultBundle : Bundle,
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity){

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        fragments[position].arguments = resultBundle
        return fragments[position]
    }
}