package com.ttn.part_v_dialog_input_testing

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.ttn.part_v_dialog_input_testing.MainActivity.Companion.buildToastMessage
import org.hamcrest.Matchers.not
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.util.jar.Attributes

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Test
    fun test_showDialog_captureNameInput() {

        // GIVEN
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        val EXPECTED_NAME = "Naveen"

        // EXECUTE AND VERIFY
        onView(withId(R.id.button_launch_dialog)).perform(click())

        onView(withText(R.string.dialog_title)).check(matches(isDisplayed()))

        onView(withText(R.string.text_ok)).perform(click())

        onView(withText(R.string.dialog_title)).check(matches(isDisplayed()))

        // enter some input
        onView(withId(R.id.md_input_message)).perform(typeText(EXPECTED_NAME))

        onView(withText(R.string.text_ok)).perform(click())

        // make sure dialog is gone
        onView(withText(R.string.dialog_title)).check(doesNotExist())

        // confirm name is set to textView in activity
        onView(withId(R.id.text_name)).check(matches(withText(EXPECTED_NAME)))

        // test if toast is displayed
        onView(withText(buildToastMessage(EXPECTED_NAME)))
                .inRoot(ToastMatcher())
                .check(matches(isDisplayed()))
    }
}