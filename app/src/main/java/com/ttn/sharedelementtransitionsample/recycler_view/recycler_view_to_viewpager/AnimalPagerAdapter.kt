package com.ttn.sharedelementtransitionsample.recycler_view.recycler_view_to_viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.ttn.sharedelementtransitionsample.recycler_view.AnimalItem
import com.ttn.sharedelementtransitionsample.recycler_view.recycler_view_to_fragment.AnimalDetailFragment.Companion.newInstance

class AnimalPagerAdapter internal constructor(
    fm: FragmentManager?,
    private val animalItems: List<AnimalItem>
) : FragmentStatePagerAdapter(fm!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        val animalItem = animalItems[position]
        return newInstance(animalItem, animalItem.name)
    }

    override fun getCount(): Int {
        return animalItems.size
    }

}