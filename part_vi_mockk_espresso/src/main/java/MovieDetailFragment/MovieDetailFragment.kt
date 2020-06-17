package MovieDetailFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ttn.part_vi_mockk_espresso.data.Movie
import com.ttn.part_vi_mockk_espresso.R
import com.ttn.part_vi_mockk_espresso.data.source.MoviesDataSource
import com.ttn.part_vi_mockk_espresso.ui.movie.DirectorsFragment
import com.ttn.part_vi_mockk_espresso.ui.movie.StarActorsFragment
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class MovieDetailFragment
constructor(
        private val requestOptions: RequestOptions,
        private val moviesDataSource: MoviesDataSource
): Fragment(){

    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { args ->
            args.getInt("movie_id").let{ movieId ->
                moviesDataSource.getMovie(movieId)?.let{ movieFromRemote ->
                    movie = movieFromRemote
                }
            }
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMovieDetails()

        movie_directiors.setOnClickListener {
            val bundle = Bundle()
            bundle.putStringArrayList("args_directors", movie.directors)
            activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, DirectorsFragment::class.java, bundle)
                    ?.addToBackStack("DirectorsFragment")
                    ?.commit()
        }

        movie_star_actors.setOnClickListener {
            val bundle = Bundle()
            bundle.putStringArrayList("args_actors", movie.star_actors)
            activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, StarActorsFragment::class.java, bundle)
                    ?.addToBackStack("StarActorsFragment")
                    ?.commit()
        }
    }

    private fun setMovieDetails(){
        Glide.with(this)
                .applyDefaultRequestOptions(requestOptions)
                .load(movie.image)
                .into(movie_image)
        movie_title.text = movie.title
        movie_description.text = movie.description
    }

}




