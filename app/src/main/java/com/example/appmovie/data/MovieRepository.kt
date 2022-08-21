package com.example.appmovie.data


import com.example.appmovie.model.Movie
import com.example.appmovie.model.PopularMovies
import com.example.appmovie.response.RepositoryError
import com.example.appmovie.response.RepositoryResponse
import com.example.appmovie.response.ResponseListener

class MovieRepository(
    private val localDataSource: MovieLocalDataSource,
    private val remoteDataSource: MovieRemoteDataSource
) {


    fun getPopular(onResult:(List<Movie>)->Unit) {
        this.remoteDataSource.getPopular(object :ResponseListener<PopularMovies> {
            override fun onResponse(response: RepositoryResponse<PopularMovies>) {
                onResult(response.data.movies)
                localDataSource.save(response.data.movies)
            }

            override fun onError(repositoryError: RepositoryError) {
                onResult(localDataSource.get())
            }

        })
    }
}
