package com.example.appmovie.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appmovie.data.MovieLocalDataSource
import com.example.appmovie.data.MovieRemoteDataSource
import com.example.appmovie.data.MovieRepository

class MovieViewModelFactory( private val context: Context):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val localDataSource = MovieLocalDataSource(context)
        val remoteDataSource= MovieRemoteDataSource()
        val repository = MovieRepository(localDataSource,remoteDataSource)
        return MoviesViewModel(repository) as T
    }

}