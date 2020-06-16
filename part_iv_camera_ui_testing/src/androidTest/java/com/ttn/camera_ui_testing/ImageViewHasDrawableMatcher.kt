package com.ttn.camera_ui_testing

import android.view.View
import android.widget.ImageView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description


/*
 * Created by Naveen Verma on 16/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

/**
 * check the presence of particular view inside another view -- BoundedMatcher
 */
object ImageViewHasDrawableMatcher {

    fun hasDrawable(): BoundedMatcher<View, ImageView> {
        return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("has drawable")
            }

            override fun matchesSafely(item: ImageView?): Boolean {
              return item?.drawable != null
            }

        }
    }
}