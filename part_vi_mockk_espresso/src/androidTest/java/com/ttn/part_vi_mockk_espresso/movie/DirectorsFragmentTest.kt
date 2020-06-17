package com.ttn.part_vi_mockk_espresso.movie

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.ttn.part_vi_mockk_espresso.R
import com.ttn.part_vi_mockk_espresso.factory.MovieFragmentFactory
import kotlinx.android.synthetic.main.fragment_directors.view.*
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DirectorsFragmentTest{

    @Test
    fun test_isDirectorsListVisible() {

        // GIVEN
        val directors =   arrayListOf("R.J. Stewart", "James Vanderbilt")
        val fragmentFactory = MovieFragmentFactory(null,null)
        val bundle = Bundle()
        bundle.putStringArrayList("args_directors", directors)
        val scenario = launchFragmentInContainer<DirectorsFragment>(
                fragmentArgs = bundle,
                factory = fragmentFactory
        )

        // VERIFY
        onView(withId(R.id.directors_text))
                .check(matches(withText(DirectorsFragment.stringBuilderForDirectors(directors))))
    }
}