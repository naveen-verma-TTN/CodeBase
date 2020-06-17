package com.ttn.part_vi_mockk_espresso.data.source

import com.ttn.part_vi_mockk_espresso.data.Movie

interface MoviesDataSource {

    fun getMovie(movieId: Int): Movie?

    fun getMovies(): List<Movie>
}