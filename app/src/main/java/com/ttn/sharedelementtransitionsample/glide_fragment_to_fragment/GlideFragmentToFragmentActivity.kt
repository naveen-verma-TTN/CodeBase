package com.ttn.sharedelementtransitionsample.glide_fragment_to_fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ttn.sharedelementtransitionsample.R
import com.ttn.sharedelementtransitionsample.glide_fragment_to_fragment.GlideFragmentA.Companion.newInstance

class GlideFragmentToFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_to_fragment)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.content, newInstance())
                .commit()
        }
    }
}