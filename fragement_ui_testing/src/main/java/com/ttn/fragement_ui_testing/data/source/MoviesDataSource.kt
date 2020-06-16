package com.ttn.fragement_ui_testing.data.source

import com.ttn.fragement_ui_testing.data.Movie

interface MoviesDataSource {

    fun getMovie(movieId: Int): Movie?
}