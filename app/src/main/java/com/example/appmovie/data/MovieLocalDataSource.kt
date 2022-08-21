package com.example.appmovie.data

import android.content.Context
import com.example.appmovie.model.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// archivo guardas las llaves valor para que no se pierda cuando se cierre la aplicacion


private const val KEY = "informacion_movie"
class MovieLocalDataSource(context:Context) {

    //nombre del archivo sp archivo_de_las_movies
    private val sharedPreferences =context.getSharedPreferences("archivo_de_las_movies",Context.MODE_PRIVATE)

    fun save(valor: List<Movie>) {
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val moviesJson=gson.toJson(valor)
        editor.putString(KEY,moviesJson)
        editor.apply()
    }

    fun get():List<Movie> {
        var movieJson = sharedPreferences.getString(KEY,"[]")
        val gson = Gson()
        val itemType = object : TypeToken<List<Movie>>() {}.type
        return gson.fromJson(movieJson, itemType)
    }
}