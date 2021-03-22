package com.ttn.advance_koin

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ttn.advance_koin.model.entity.GithubUser
import com.ttn.advance_koin.utils.LoadingState
import com.ttn.advance_koin.view.RecyclerAdapter
import com.ttn.advance_koin.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG: String = "MainActivity"
    }

    private val userViewModel by viewModel<UserViewModel>()

    private val adapter by lazy {
        RecyclerAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()

        subscribeObservers()


    }

    private fun subscribeObservers() {
        userViewModel.data.observe(this, Observer { githubUsers ->
            adapter.setUsers(githubUsers)
            adapter.notifyDataSetChanged()
        })

        userViewModel.loadingState.observe(this, Observer {
            when (it.status) {
                LoadingState.Status.FAILED -> Toast.makeText(
                    baseContext,
                    it.msg,
                    Toast.LENGTH_SHORT
                ).show()
                LoadingState.Status.RUNNING -> Toast.makeText(
                    baseContext,
                    "Loading",
                    Toast.LENGTH_SHORT
                ).show()
                LoadingState.Status.SUCCESS -> Toast.makeText(
                    baseContext,
                    "Success",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun initRecycler() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }
}
