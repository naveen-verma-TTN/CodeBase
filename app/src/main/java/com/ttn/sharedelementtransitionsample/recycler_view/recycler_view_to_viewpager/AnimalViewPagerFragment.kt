package com.ttn.sharedelementtransitionsample.recycler_view.recycler_view_to_viewpager

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.ttn.sharedelementtransitionsample.R
import com.ttn.sharedelementtransitionsample.recycler_view.AnimalItem
import java.util.*

class AnimalViewPagerFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postponeEnterTransition()
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(android.R.transition.move)
        sharedElementReturnTransition = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_animal_view_pager, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        val currentItem =
            arguments!!.getInt(EXTRA_INITIAL_ITEM_POS)
        val animalItems: ArrayList<AnimalItem> =
            arguments!!.getParcelableArrayList(EXTRA_ANIMAL_ITEMS)!!
        val animalPagerAdapter =
            AnimalPagerAdapter(childFragmentManager, animalItems)
        val viewPager =
            view.findViewById<View>(R.id.animal_view_pager) as ViewPager
        viewPager.adapter = animalPagerAdapter
        viewPager.currentItem = currentItem
    }

    companion object {
        private const val EXTRA_INITIAL_ITEM_POS = "initial_item_pos"
        private const val EXTRA_ANIMAL_ITEMS = "animal_items"
        @JvmStatic
        fun newInstance(
            currentItem: Int,
            animalItems: ArrayList<AnimalItem>
        ): AnimalViewPagerFragment {
            val animalViewPagerFragment = AnimalViewPagerFragment()
            val bundle = Bundle()
            bundle.putInt(EXTRA_INITIAL_ITEM_POS, currentItem)
            bundle.putParcelableArrayList(
                EXTRA_ANIMAL_ITEMS,
                animalItems
            )
            animalViewPagerFragment.arguments = bundle
            return animalViewPagerFragment
        }
    }
}