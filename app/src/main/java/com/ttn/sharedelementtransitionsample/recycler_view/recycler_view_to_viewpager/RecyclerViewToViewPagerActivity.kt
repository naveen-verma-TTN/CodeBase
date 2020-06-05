package com.ttn.sharedelementtransitionsample.recycler_view.recycler_view_to_viewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ttn.sharedelementtransitionsample.R
import com.ttn.sharedelementtransitionsample.recycler_view.recycler_view_to_viewpager.RecyclerViewFragment.Companion.newInstance

class RecyclerViewToViewPagerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_to_fragment)
        supportFragmentManager
            .beginTransaction()
            .add(
                R.id.content,
                newInstance()
            )
            .commit()
    }
}