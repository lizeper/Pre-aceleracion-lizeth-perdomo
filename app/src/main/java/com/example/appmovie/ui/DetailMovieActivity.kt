package com.example.appmovie.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.appmovie.model.Movie
import com.example.appmovie.databinding.ActivityDetailMovieBinding


class DetailMovieActivity : AppCompatActivity() {

    companion object{
        const val KEY_MOVIE = "KEY_MOVIE"
    }

    private lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //obtner la pelicula que nos enviaron desde MovieActivity
        // crea una variable para guardar la pelicula, el as para convertir de serializable a movie
        val movie = intent.getSerializableExtra(KEY_MOVIE) as Movie

        binding = ActivityDetailMovieBinding.inflate(this.layoutInflater)

        binding.titleMoviesDetalle.text = movie.title

        Glide.with(binding.imageMovie)
            .load("https://image.tmdb.org/t/p/original/${movie.imagen}")
            .centerCrop()
            .into(binding.imageMovie);

        binding.voteAverage.text = movie.voteAverage.toString()
        binding.Description.text = movie.overview

        setContentView(binding.root)
    }
}