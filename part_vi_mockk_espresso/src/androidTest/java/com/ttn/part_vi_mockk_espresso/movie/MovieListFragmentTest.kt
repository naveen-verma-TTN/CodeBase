package com.ttn.part_vi_mockk_espresso.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.ttn.part_vi_mockk_espresso.R
import com.ttn.part_vi_mockk_espresso.data.FakeMovieData
import kotlinx.android.synthetic.main.fragment_movie_list.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MovieListFragmentTest {

    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    val LIST_ITEM_IN_TEST = 4
    val MOIVE_IN_TEST = FakeMovieData.movies[LIST_ITEM_IN_TEST]

    /**
     * RecyclerView come into view
     */
    @Test
    fun test_isListFragmentVisible_onAppLaunch() {

        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    /**
     * Select list item, nav to DetailFragment
     * Correct movie is in view
     */
    @Test
    fun test_selectListItem_isDetailFragmentVisible() {

        onView(withId(R.id.recycler_view))
                .perform(actionOnItemAtPosition<MoviesListAdapter.MovieViewHolder>(
                        LIST_ITEM_IN_TEST,
                        click())
                )

        // VERIFY
        onView(withId(R.id.movie_title)).check(matches(withText(MOIVE_IN_TEST.title)))
    }

    /**
     * select list item , nav to DetailFragment
     * pressBack
     */
    @Test
    fun test_backNavigation_toMovieListFragment() {

        onView(withId(R.id.recycler_view))
                .perform(actionOnItemAtPosition<MoviesListAdapter.MovieViewHolder>(
                        LIST_ITEM_IN_TEST,
                        click())
                )

        // VERIFY
        onView(withId(R.id.movie_title)).check(matches(withText(MOIVE_IN_TEST.title)))

        pressBack()

        // VERIFY
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    /**
     * select list item , nav to DetailFragment
     * select director TextView, nav to DirectorsFragment
     */
    @Test
    fun test_navDirectorFragment_validateDirectorsList() {

        onView(withId(R.id.recycler_view))
                .perform(actionOnItemAtPosition<MoviesListAdapter.MovieViewHolder>(
                        LIST_ITEM_IN_TEST,
                        click())
                )

        // VERIFY
        onView(withId(R.id.movie_title)).check(matches(withText(MOIVE_IN_TEST.title)))

        onView(withId(R.id.movie_directiors)).perform(click())

        val directors = MOIVE_IN_TEST.directors
        val verifyDirectorValue = DirectorsFragment.stringBuilderForDirectors(directors!!)

        onView(withId(R.id.directors_text))
                .check(matches(withText(verifyDirectorValue)))

    }
    /**
     * select list item , nav to ActorsFragment
     * select director TextView, nav to ActorsFragment
     */

    @Test
    fun test_navStarActorsFragment_validateActorsList() {

        onView(withId(R.id.recycler_view))
                .perform(actionOnItemAtPosition<MoviesListAdapter.MovieViewHolder>(
                        LIST_ITEM_IN_TEST,
                        click())
                )

        // VERIFY
        onView(withId(R.id.movie_title)).check(matches(withText(MOIVE_IN_TEST.title)))

        onView(withId(R.id.movie_star_actors)).perform(click())

        val actors = MOIVE_IN_TEST.star_actors
        val verifyActorsValue = StarActorsFragment.stringBuilderForStarActors(actors!!)

        onView(withId(R.id.star_actors_text))
                .check(matches(withText(verifyActorsValue)))

    }
}