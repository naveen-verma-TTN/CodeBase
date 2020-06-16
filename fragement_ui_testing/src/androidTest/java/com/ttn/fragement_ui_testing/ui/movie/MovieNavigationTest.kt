package com.ttn.fragement_ui_testing.ui.movie

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.ttn.fragement_ui_testing.R
import kotlinx.android.synthetic.main.fragment_movie_detail.view.*
import org.junit.Test
import org.junit.runner.RunWith


/*
 * Created by Naveen Verma on 16/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieNavigationTest{

    /**
     * fun to check for correct navigation of fragment
     */
    @Test
    fun test_movieFragmentsNavigation() {

        // SETUP
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // DetailFragment -> DirectorsFragment
        onView(withId(R.id.movie_directiors))
                .perform(click())

        // VERIFY
        onView(withId(R.id.fragment_movie_directors_parent))
                .check(matches(isDisplayed()))

        // Nav to back
        pressBack()

        // VERIFY
        onView(withId(R.id.fragment_movie_detail_parent))
                .check(matches(isDisplayed()))


        // DetailFragment -> StarActorFragment
        onView(withId(R.id.movie_star_actors))
                .perform(click())

        // VERIFY
        onView(withId(R.id.fragment_movie_star_actors_parent))
                .check(matches(isDisplayed()))

        // Nav to back
        pressBack()

        // VERIFY
        onView(withId(R.id.fragment_movie_detail_parent))
                .check(matches(isDisplayed()))


    }
}