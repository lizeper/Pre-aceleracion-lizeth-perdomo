package com.example.appmovie.data

import com.example.appmovie.BuildConfig
import com.example.appmovie.model.PopularMovies
import com.example.appmovie.response.RepositoryError
import com.example.appmovie.response.RepositoryResponse
import com.example.appmovie.response.ResponseListener
import com.example.appmovie.response.Source
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRemoteDataSource {

    fun getPopular(listener: ResponseListener<PopularMovies>) {
        //instancia de retrofit
        val service = RetrofitService.instance
            //servicio y la interface
            .create(MoviesService::class.java)
            .getPopular(BuildConfig.API_KEY,BuildConfig.API_language)




        service.enqueue(object : Callback<PopularMovies> {
            override fun onResponse(call: Call<PopularMovies>, response: Response<PopularMovies>) {
                val popularMovies = response.body()

                if (response.isSuccessful && popularMovies != null) {
                    listener.onResponse(
                        RepositoryResponse(
                            data = popularMovies,
                            source = Source.REMOTE
                        )
                    )
                } else {
                    listener.onError(
                        RepositoryError(
                            message = "El servidor rechazó la solicitud",
                            code = response.code(),
                            source = Source.REMOTE
                        )
                    )
                }
            }

            override fun onFailure(call: Call<PopularMovies>, t: Throwable) {
                listener.onError(
                    RepositoryError(
                        message = t.message ?: "Error inesperado",
                        code = -1,
                        source = Source.REMOTE
                    )
                )
            }
        })
    }
}

