package com.ttn.fragement_ui_testing.ui.movie

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.ttn.fragement_ui_testing.R
import com.ttn.fragement_ui_testing.data.DummyMovies.THE_RUNDOWN
import com.ttn.fragement_ui_testing.factory.MovieFragmentFactory
import kotlinx.android.synthetic.main.layout_movie_list_item.view.*
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieDetailFragmentTest {

    /**
     * fun to test if the correct bundle is passed to the MovieDetailFragment
     */
    @Test
    fun test_isMovieDataVisible() {

        // SETUP
        val movie = THE_RUNDOWN
        val fragmentFactory = MovieFragmentFactory()
        val bundle = Bundle()
        bundle.putInt("movie_id", movie.id)

        val scenario = launchFragmentInContainer<MovieDetailFragment>(
            fragmentArgs = bundle,
                factory = fragmentFactory
        )

        onView(withId(R.id.movie_title)).check(matches(withText(movie.title)))

        onView(withId(R.id.movie_description)).check(matches(withText(movie.description)))

    }
}