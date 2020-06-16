package com.ttn.gallery_ui_testing

import android.app.Activity.RESULT_OK
import android.app.Instrumentation
import android.content.ContentResolver
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.provider.MediaStore
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @get: Rule
    val intentsTestRule = IntentsTestRule(MainActivity::class.java)

    /**
     * fun for checking the correct intent result
     */
    @Test
    fun test_validateGalleryIntent() {

        // GIVEN --- use of Hamcrest
        val expectedIntent: Matcher<Intent> = allOf(
                hasAction(Intent.ACTION_PICK),
                hasData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        )

        val activityResult = createGalleryPickActivityResultStub()
        intending(expectedIntent)
                .respondWith(activityResult) // mapping the expected result with the activityResult (Dummy result)

        // EXECUTE AND VERIFY
        onView(withId(R.id.button_open_gallery)).perform(click())
        intended(expectedIntent)
    }

    /**
     * dummy result for Intent picker
     */
    private fun createGalleryPickActivityResultStub(): Instrumentation.ActivityResult {
        val resources: Resources = InstrumentationRegistry.getInstrumentation()
                .context.resources

        // dummy uri
        val imageUri = Uri.parse(
                ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                        resources.getResourcePackageName(R.drawable.ic_launcher_background) + "/" +
                        resources.getResourceTypeName(R.drawable.ic_launcher_background) + "/" +
                        resources.getResourceEntryName(R.drawable.ic_launcher_background)
        )
        val resultIntent = Intent()
        resultIntent.data = imageUri
        return Instrumentation.ActivityResult(RESULT_OK, resultIntent)
    }
}