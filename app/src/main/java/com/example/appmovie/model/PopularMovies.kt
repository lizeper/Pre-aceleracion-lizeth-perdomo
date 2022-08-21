package com.example.appmovie.model

import com.google.gson.annotations.SerializedName

data class PopularMovies (
    @SerializedName("page")
    val page :Int,
    @SerializedName("results")
    val movies:List<Movie>
)