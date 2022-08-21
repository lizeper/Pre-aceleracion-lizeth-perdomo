package com.example.appmovie.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.appmovie.MovieAdapter
import com.example.appmovie.databinding.ActivityMoviesBinding
import com.example.appmovie.model.Movie
import com.example.appmovie.ui.DetailMovieActivity.Companion.KEY_MOVIE

class MoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding
    private val viewModel: MoviesViewModel by viewModels(
        factoryProducer = { MovieViewModelFactory(this) }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMoviesBinding.inflate(this.layoutInflater)

        val adapter = MovieAdapter(listOf(), object : MovieAdapter.MovieListener {
            override fun select(movie: Movie) {
                val intent = Intent(this@MoviesActivity, DetailMovieActivity::class.java).apply {
                    putExtra(KEY_MOVIE, movie)
                }
                startActivity(intent)
            }
        })

        binding.recyclerMovies.adapter = adapter

        viewModel.movies.observe(this, {
            adapter.listOfMovie = it
            adapter.notifyDataSetChanged()
            binding.recyclerMovies.visibility = View.VISIBLE
            binding.messageError.visibility = View.GONE
            binding.refreshButton.visibility = View.GONE
        })

        viewModel.errorMovie.observe(this, {
            binding.recyclerMovies.visibility = View.GONE
            binding.messageError.visibility = View.VISIBLE
            binding.refreshButton.visibility = View.VISIBLE
        })

        binding.refreshButton.setOnClickListener {
            binding.recyclerMovies.visibility = View.GONE
            binding.messageError.visibility = View.GONE
            binding.refreshButton.visibility = View.GONE
            viewModel.getMovie()
        }

        viewModel.getMovie()

        setContentView(binding.root)
    }
}