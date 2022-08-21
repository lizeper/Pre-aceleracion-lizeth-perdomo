package com.example.appmovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appmovie.model.Movie
import com.example.appmovie.databinding.ItemMoviesBinding

class MovieAdapter(
    var listOfMovie:List<Movie>,
    val listener: MovieListener
    ):RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    interface MovieListener {
        fun select(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {

        //inflar la vista de  un item de la lista y crear una viewholder
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding =ItemMoviesBinding.inflate(layoutInflater,parent,false)
        return MovieViewHolder(binding,listener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listOfMovie.get(position))
    }

    override fun getItemCount(): Int {
       //scroll para mostrar al usaurio
        return listOfMovie.size
    }

    class MovieViewHolder(private val binding: ItemMoviesBinding, val listener: MovieListener): RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie){
            binding.titleMovie.text=movie.title

            Glide.with(binding.imageMovie)
                .load("https://image.tmdb.org/t/p/original/${movie.imagen}")
                .centerCrop()
                .into(binding.imageMovie);

            binding.voteAverage.text= movie.voteAverage.toString()
            binding.Description.text=movie.overview

            binding.root.setOnClickListener {
                listener.select(movie)
            }
        }
    }

}