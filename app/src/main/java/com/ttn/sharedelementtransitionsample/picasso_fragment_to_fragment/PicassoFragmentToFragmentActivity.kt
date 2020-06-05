package com.ttn.sharedelementtransitionsample.picasso_fragment_to_fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ttn.sharedelementtransitionsample.R
import com.ttn.sharedelementtransitionsample.picasso_fragment_to_fragment.PicassoFragmentA.Companion.newInstance

class PicassoFragmentToFragmentActivity : AppCompatActivity() {
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