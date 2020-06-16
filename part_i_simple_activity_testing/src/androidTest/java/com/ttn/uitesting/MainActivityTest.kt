package com.ttn.uitesting

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    /**
     * Navigation test: MainActivity -> SecondaryActivity
     */
    @Test
    fun test_navSecondaryActivity() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.button_next_activity))
                .perform(click())

        onView(withId(R.id.secondary))
                .check(matches(isDisplayed()))
    }

    @Test
    fun test_backPress_toMainActivity() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.button_next_activity))
                .perform(click())

        onView(withId(R.id.secondary))
                .check(matches(isDisplayed()))

        onView(withId(R.id.button_back))
                .perform(click()) // method 1

//        pressBack() // method 2

        onView(withId(R.id.main))
                .check(matches(isDisplayed()))
    }

    /**
     * fun to check if the activity is render or not
     */
    @Test
    fun test_isActivityInView() {

        // activity object
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // check for the view is render
        onView(withId(R.id.main))
                .check(matches(isDisplayed()))
    }

    /**
     * fun to check the visibility of title and next button
     */
    @Test
    fun test_visibility_title_nextButton() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.activity_main_title))
                .check(matches(isDisplayed()))

        onView(withId(R.id.button_next_activity))
                .check(matches(isDisplayed())) // method 1

        onView(withId(R.id.button_next_activity))
                .check(matches(withEffectiveVisibility(Visibility.VISIBLE))) // method 2
    }

    /**
     * fun to check if title is displayed correct text
     */
    @Test
    fun test_isTitleTextDisplayed() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.activity_main_title))
                .check(matches(withText(R.string.text_mainactivity)))
    }
}