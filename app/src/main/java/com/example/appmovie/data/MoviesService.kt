package com.example.appmovie.data

import com.example.appmovie.model.PopularMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    //obtener GET las peliculas populares y anexar movies/popular de la api
    @GET("movie/popular")
    fun getPopular(@Query("api_key")apiKey:String,@Query("language")lenguage:String): Call<PopularMovies>
}