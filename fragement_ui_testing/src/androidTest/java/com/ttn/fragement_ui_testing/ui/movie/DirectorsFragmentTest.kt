package com.ttn.fragement_ui_testing.ui.movie

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.ttn.fragement_ui_testing.R
import com.ttn.fragement_ui_testing.factory.MovieFragmentFactory
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.nio.BufferUnderflowException

@RunWith(AndroidJUnit4ClassRunner::class)
class DirectorsFragmentTest{

    /**
     * fun to test if the correct bundle is passed to the DirectorsFragment
     */
    @Test
    fun test_isDirectorsListVisible() {

        // SETUP
        val directors = arrayListOf("R.J. Stewart", "James Vanderbilt")
        val fragmentFactory = MovieFragmentFactory()
        val bundle = Bundle()
        bundle.putStringArrayList("args_directors", directors)

        val scenario = launchFragmentInContainer<DirectorsFragment>(
                fragmentArgs = bundle,
                factory = fragmentFactory
        )

        // VERIFY
        onView(withId(R.id.directors_text))
                .check(matches(withText(
                        DirectorsFragment.stringBuilderForDirectors(directors)
                )))
    }
}