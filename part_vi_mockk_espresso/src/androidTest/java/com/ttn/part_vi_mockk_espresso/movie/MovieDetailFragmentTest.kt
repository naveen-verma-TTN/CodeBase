package com.ttn.part_vi_mockk_espresso.movie

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.bumptech.glide.request.RequestOptions
import com.ttn.part_vi_mockk_espresso.R
import com.ttn.part_vi_mockk_espresso.data.Movie
import com.ttn.part_vi_mockk_espresso.data.source.MoviesRemoteDataSource
import com.ttn.part_vi_mockk_espresso.factory.MovieFragmentFactory
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * only work on API > 29
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class MovieDetailFragmentTest{

    /**
     * fun to check for Movie Data is visible
     */
    @Test
    fun test_isMovieDataVisible() {

        // SETUP
        val movieId = 1
        val title = "The Rundown"
        val description = "A tough aspiring chef is hired to bring home a mobster's son from the Amazon but " +
                "becomes involved in the fight against an oppressive town operator and the search " +
                "for a legendary treasure."
        val movie = Movie(
                movieId,
                title,
                "https://nyc3.digitaloceanspaces.com/open-api-spaces/open-api-static/blog/1/The_Rundown-the_rundown.png",
                description ,
                arrayListOf("R.J. Stewart", "James Vanderbilt"),
                arrayListOf("Dwayne Johnson", "Seann William Scott", "Rosario Dawson", "Christopher Walken")
        )

        // mocking the dataSource
        val moviesDataSource = mockk<MoviesRemoteDataSource>()
        every {
            moviesDataSource.getMovie(movieId)
        } returns movie

        // Building the dependencies
        val requestOptions = RequestOptions()
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_image)
        val fragmentFactory = MovieFragmentFactory(
                requestOptions,
                moviesDataSource
        )

        val bundle = Bundle()
        bundle.putInt("movie_id", movieId)
        val scenario = launchFragmentInContainer<MovieDetailFragment>(
                fragmentArgs = bundle,
                factory = fragmentFactory
        )

        // VERIFY
        onView(withId(R.id.movie_title)).check(matches(withText(title)))

        onView(withId(R.id.movie_description)).check(matches(withText(description)))
    }
}