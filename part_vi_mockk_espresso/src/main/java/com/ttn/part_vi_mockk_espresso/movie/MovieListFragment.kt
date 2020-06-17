package com.ttn.part_vi_mockk_espresso.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ttn.part_vi_mockk_espresso.R
import com.ttn.part_vi_mockk_espresso.data.Movie
import com.ttn.part_vi_mockk_espresso.data.source.MoviesDataSource
import com.ttn.part_vi_mockk_espresso.utils.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_movie_list.*


/*
 * Created by Naveen Verma on 17/6/20.
 * Company name: To The New
 * Email ID: naveen.verma@tothenew.com
 */

class MovieListFragment(
        val moviesDataSource: MoviesDataSource
) : Fragment(),
        MoviesListAdapter.Interaction
{
    override fun onItemSelected(position: Int, item: Movie) {
        activity?.run {
            val bundle = Bundle()
            bundle.putInt("movie_id", item.id)
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MovieDetailFragment::class.java, bundle)
                    .addToBackStack("MovieDetailFragment")
                    .commit()
        }
    }

    lateinit var listAdapter: MoviesListAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        getData()
    }

    private fun getData(){
        listAdapter.submitList(moviesDataSource.getMovies())
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(activity)
            removeItemDecoration(TopSpacingItemDecoration(30))
            addItemDecoration(TopSpacingItemDecoration(30))
            listAdapter = MoviesListAdapter(this@MovieListFragment)
            adapter = listAdapter
        }
    }


}
