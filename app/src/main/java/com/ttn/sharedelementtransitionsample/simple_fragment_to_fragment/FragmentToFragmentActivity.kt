package com.ttn.sharedelementtransitionsample.simple_fragment_to_fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ttn.sharedelementtransitionsample.R

class FragmentToFragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_to_fragment)
        supportFragmentManager
            .beginTransaction()
            .add(R.id.content, SimpleFragmentA.newInstance())
            .commit()
    }
}