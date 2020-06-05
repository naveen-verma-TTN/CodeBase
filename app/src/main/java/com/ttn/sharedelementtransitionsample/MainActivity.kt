package com.ttn.sharedelementtransitionsample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.ttn.sharedelementtransitionsample.glide_activity_to_activity.GlideActivityA
import com.ttn.sharedelementtransitionsample.glide_fragment_to_fragment.GlideFragmentToFragmentActivity
import com.ttn.sharedelementtransitionsample.picasso_activity_to_activity.PicassoActivityA
import com.ttn.sharedelementtransitionsample.picasso_fragment_to_fragment.PicassoFragmentToFragmentActivity
import com.ttn.sharedelementtransitionsample.recycler_view.recycler_view_to_activity.RecyclerViewActivity
import com.ttn.sharedelementtransitionsample.recycler_view.recycler_view_to_fragment.RecyclerViewToFragmentActivity
import com.ttn.sharedelementtransitionsample.recycler_view.recycler_view_to_viewpager.RecyclerViewToViewPagerActivity
import com.ttn.sharedelementtransitionsample.simple_activity_to_activity.SimpleActivityA
import com.ttn.sharedelementtransitionsample.simple_fragment_to_fragment.FragmentToFragmentActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val activityToActivityBtn =
            findViewById<View>(R.id.activity_to_activity_btn) as Button
        activityToActivityBtn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    SimpleActivityA::class.java
                )
            )
        }
        val fragmentToFragmentBtn =
            findViewById<View>(R.id.fragment_to_fragment_btn) as Button
        fragmentToFragmentBtn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    FragmentToFragmentActivity::class.java
                )
            )
        }
        val picassoActivityBtn =
            findViewById<View>(R.id.picasso_activity_to_activity_btn) as Button
        picassoActivityBtn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    PicassoActivityA::class.java
                )
            )
        }
        val picassoFragmentBtn =
            findViewById<View>(R.id.picasso_fragment_to_fragment_btn) as Button
        picassoFragmentBtn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    PicassoFragmentToFragmentActivity::class.java
                )
            )
        }
        val glideActivityBtn =
            findViewById<View>(R.id.glide_activity_to_activity_btn) as Button
        glideActivityBtn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    GlideActivityA::class.java
                )
            )
        }
        val glideFragmentBtn =
            findViewById<View>(R.id.glide_fragment_to_fragment_btn) as Button
        glideFragmentBtn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    GlideFragmentToFragmentActivity::class.java
                )
            )
        }
        val recyclerViewToAcitivtyBtn =
            findViewById<View>(R.id.recycler_view_to_activity_btn) as Button
        recyclerViewToAcitivtyBtn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    RecyclerViewActivity::class.java
                )
            )
        }
        val recyclerViewToFragmentBtn =
            findViewById<View>(R.id.recycler_view_to_fragment_btn) as Button
        recyclerViewToFragmentBtn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    RecyclerViewToFragmentActivity::class.java
                )
            )
        }
        val recyclerViewToViewPagerBtn =
            findViewById<View>(R.id.recycler_view_to_view_pager_btn) as Button
        recyclerViewToViewPagerBtn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    RecyclerViewToViewPagerActivity::class.java
                )
            )
        }
    }

    override fun onBackPressed() {
        supportFragmentManager.popBackStack()
        if (supportFragmentManager.backStackEntryCount == 0) {
            super.onBackPressed()
        }
    }
}