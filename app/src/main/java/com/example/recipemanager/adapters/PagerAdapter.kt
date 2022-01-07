package com.example.recipemanager.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(
    private val titles : ArrayList<String>,
    private val fragments : ArrayList<Fragment>,
    private val resultBundle : Bundle,
    fm : FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    override fun getCount(): Int {
        return fragments.size
    }


    override fun getItem(position: Int): Fragment {
        fragments[position].arguments = resultBundle
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}