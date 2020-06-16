package com.ttn.fragement_ui_testing.ui.movie

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.ttn.fragement_ui_testing.R
import com.ttn.fragement_ui_testing.factory.MovieFragmentFactory
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class StarActorsFragmentTest{

    /**
     * fun to test if the correct bundle is passed to the StarFragment
     */
    @Test
    fun test_isStarListVisible() {

        // SETUP
        val stars =  arrayListOf("Dwayne Johnson",
                "Seann William Scott",
                "Rosario Dawson",
                "Christopher Walken")
        val fragmentFactory = MovieFragmentFactory()
        val bundle = Bundle()
        bundle.putStringArrayList("args_actors", stars)

        val scenario = launchFragmentInContainer<StarActorsFragment>(
                fragmentArgs = bundle,
                factory = fragmentFactory
        )

        // VERIFY
        Espresso.onView(ViewMatchers.withId(R.id.star_actors_text))
                .check(ViewAssertions.matches(ViewMatchers.withText(
                        StarActorsFragment.stringBuilderForStarActors(stars)
                )))
    }
}