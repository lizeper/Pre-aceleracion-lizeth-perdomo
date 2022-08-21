package com.example.appmovie.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appmovie.data.MovieRepository
import com.example.appmovie.model.Movie

class MoviesViewModel (
    private val repository: MovieRepository
    ): ViewModel(){

    //liveData expone al activity
    val movies:MutableLiveData<List<Movie>> = MutableLiveData()
    val errorMovie:MutableLiveData<String> = MutableLiveData()

    fun getMovie(){
        repository.getPopular(){

            if (it.isEmpty()){
                errorMovie.postValue("")
            }else{
                movies.postValue(it)
            }
        }
    }
}
